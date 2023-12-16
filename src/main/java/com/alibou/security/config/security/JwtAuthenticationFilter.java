package com.alibou.security.config.security;

import com.alibou.security.exception.TokenException;
import com.alibou.security.model.entity.Token;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.alibou.security.util.ErrorCode.TOKEN_PROBLEM;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
@Primary
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RedisTemplate<String, Token> redisTemplate;
    private HashOperations<String, String, Token> hashOperations;
    @Value("${redis.cache-name}")
    private String cacheName;
    @Value("${application.security.allow-paths}")
    private List<String> allowPaths;

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            boolean isAllow = allowPaths.stream()
                    .anyMatch(path -> request.getServletPath()
                            .contains(path.replaceAll("\\*", "")) &&
                            !request.getServletPath().contains("register"));
            if (isAllow) {
                filterChain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userEmail;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                var token = hashOperations.get(cacheName, userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    if (token == null || !jwt.equals(token.getToken())) {
                        throw TokenException.expired();
                    }
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            var responseDto = ResponseDto.error(e.getMessage());
            if (e.getStatus().equals(HttpStatus.UNAUTHORIZED)) {
                responseDto = responseDto.toBuilder().errorCode(TOKEN_PROBLEM).build();
            }
            response.setStatus(e.getStatus().value());
            response.setContentType(APPLICATION_JSON.toString());
            response.getWriter().write(new ObjectMapper()
                    .writeValueAsString(responseDto));
        }
    }
}
