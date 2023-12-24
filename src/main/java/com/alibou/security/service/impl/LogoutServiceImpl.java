package com.alibou.security.service.impl;

import com.alibou.security.exception.TokenException;
import com.alibou.security.model.entity.Token;
import com.alibou.security.model.repo.TokenRepository;
import com.alibou.security.service.JwtService;
import com.alibou.security.service.LogoutService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler, LogoutService {

    private final TokenRepository tokenRepository;
    private final RedisTemplate<String, Token> redisTemplate;
    private HashOperations<String, String, Token> hashOperations;
    private final JwtService jwtService;
    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
    @Value("${redis.cache-name}")
    private String cacheName;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token token;
        String login;
        try {
            login = jwtService.extractUsername(jwt);
            token = hashOperations.get(cacheName, login);
        } catch (TokenException e) {
            SecurityContextHolder.clearContext();
            return;
        }
        if (token != null) {
            hashOperations.delete(cacheName, login);
            tokenRepository.deleteAll(tokenRepository.findAllByUserID(token.getUserID()));
            SecurityContextHolder.clearContext();
        }
    }
}
