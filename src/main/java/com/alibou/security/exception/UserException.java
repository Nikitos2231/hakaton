package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

public class UserException extends GenericException {
    public UserException(String message, HttpStatus status) {
        super(message, status);
    }

    public static UserException notFoundOfEmail(String email) {
        return new UserException(String.format("User with login: %s not found", email), HttpStatus.NOT_FOUND);
    }

    public static UserException alreadyExists(String email) {
        return new UserException(String.format("User with email: %s already exists", email), HttpStatus.BAD_REQUEST);
    }

    public static UserException invalidCredentials() {
        return new UserException("Invalid credentials", HttpStatus.BAD_REQUEST);
    }
}
