package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends GenericException {

    public TokenException(String message, HttpStatus status) {
        super(message, status);
    }

    public static TokenException notFoundOfToken(String token) {
        return new TokenException(String.format("Refresh token %s not found", token), HttpStatus.NOT_FOUND);
    }

    public static TokenException invalid() {
        return new TokenException("Token is invalid", HttpStatus.BAD_REQUEST);
    }

    public static TokenException invalidStructure() {
        return new TokenException("Refresh token has invalid structure", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static TokenException expired() {
        return new TokenException("Access token is expired", HttpStatus.UNAUTHORIZED);
    }
}
