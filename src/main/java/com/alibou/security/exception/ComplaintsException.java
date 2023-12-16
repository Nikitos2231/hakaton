package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ComplaintsException extends GenericException {

    public ComplaintsException(String message, HttpStatus status) {
        super(message, status);
    }

    public static ComplaintsException notFoundByName(UUID name) {
        return new ComplaintsException(String.format("Complaints with name: %s not found", name), HttpStatus.NOT_FOUND);
    }
}
