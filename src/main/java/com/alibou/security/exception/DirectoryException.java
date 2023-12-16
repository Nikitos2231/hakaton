package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

public class DirectoryException extends GenericException {

    public DirectoryException(String message, HttpStatus status) {
        super(message, status);
    }

    public static DirectoryException notFoundByName(String name) {
        return new DirectoryException(String.format("Directory with name: %s not found", name), HttpStatus.NOT_FOUND);
    }

    public static DirectoryException alreadyExists(String name) {
        return new DirectoryException(String.format("Directory with name: %s already exists", name), HttpStatus.BAD_REQUEST);
    }
}
