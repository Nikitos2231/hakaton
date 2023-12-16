package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

public class CompanyException extends GenericException {

    public CompanyException(String message, HttpStatus status) {
        super(message, status);
    }

    public static CompanyException notFoundByName(String name) {
        return new CompanyException(String.format("Company with name: %s not found", name), HttpStatus.NOT_FOUND);
    }

    public static CompanyException alreadyExists(String name) {
        return new CompanyException(String.format("Company with name: %s already exists", name), HttpStatus.BAD_REQUEST);
    }
}
