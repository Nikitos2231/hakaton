package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TechInspectionException extends GenericException {

    public TechInspectionException(String message, HttpStatus status) {
        super(message, status);
    }

    public static TechInspectionException notFoundByName(UUID name) {
        return new TechInspectionException(String.format("Directory with name: %s not found", name), HttpStatus.NOT_FOUND);
    }
}