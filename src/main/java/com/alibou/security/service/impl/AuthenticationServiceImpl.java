package com.alibou.security.service.impl;

import com.alibou.security.exception.CompanyException;
import com.alibou.security.exception.TokenException;
import com.alibou.security.exception.UserException;
import com.alibou.security.model.entity.Token;
import com.alibou.security.model.entity.User;
import com.alibou.security.model.repo.TokenRepository;
import com.alibou.security.model.repo.UserRepository;
import com.alibou.security.rest.dto.request.AuthenticationRequest;
import com.alibou.security.rest.dto.request.RefreshTokenRequest;
import com.alibou.security.rest.dto.request.RegisterRequest;
import com.alibou.security.rest.dto.response.AuthenticationResponse;
import com.alibou.security.service.AuthenticationService;
import com.alibou.security.service.CompanyService;
import com.alibou.security.service.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, Token> redisTemplate;
    private HashOperations<String, String, Token> hashOperations;
    @Value("${redis.cache-name}")
    private String cacheName;
    @Value("${redis.cache-live}")
    private Integer cacheLive;

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        validateRegisterRequest(request);
        var user = User.builder()
                .id(UUID.randomUUID())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .company(request.getCompany())
                .build();
        var savedUser = repository.save(user);
        var accessToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserRefreshToken(savedUser, refreshToken);
        saveUserAccessToken(savedUser, accessToken);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (repository.findByLogin(request.getLogin()).isPresent()) {
            throw UserException.alreadyExists(request.getLogin());
        }
        if (companyService.findByNameOpt(request.getCompany()).isEmpty()) {
            throw CompanyException.notFoundByName(request.getCompany());
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByLogin(request.getEmail())
                .orElseThrow(() -> UserException.notFoundOfEmail(request.getEmail()));
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserRefreshToken(user, refreshToken);
        saveUserAccessToken(user, accessToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserAccessToken(User user, String accessToken) {
        var token = Token.builder()
                .id(UUID.randomUUID())
                .userID(user.getId())
                .token(accessToken)
                .build();
        hashOperations.put(cacheName, user.getLogin(), token);
        redisTemplate.expire(cacheName, Duration.ofMinutes(cacheLive));
    }

    private void saveUserRefreshToken(User user, String refreshToken) {
        tokenRepository.deleteAll(tokenRepository.findAllValidTokenByUser(user.getId()));
        var token = Token.builder()
                .id(UUID.randomUUID())
                .userID(user.getId())
                .token(refreshToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        var refreshToken = request.refreshToken();
        var userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByLogin(userEmail)
                    .orElseThrow(() -> UserException.notFoundOfEmail(userEmail));
            tokenRepository.findByToken(refreshToken)
                    .orElseThrow(() -> TokenException.notFoundOfToken(refreshToken));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                saveUserRefreshToken(user, refreshToken);
                saveUserAccessToken(user, accessToken);
                return AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
            throw TokenException.invalid();
        }
        throw TokenException.invalidStructure();
    }
}
