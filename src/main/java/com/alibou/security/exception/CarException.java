package com.alibou.security.exception;

import org.springframework.http.HttpStatus;

public class CarException extends GenericException {

    public CarException(String message, HttpStatus status) {
        super(message, status);
    }

    public static CarException notFound(String serialNumber) {
        return new CarException(String.format("Car with serial number: %s not found", serialNumber), HttpStatus.NOT_FOUND);
    }

    public static CarException alreadyExists(String serialNumber) {
        return new CarException(String.format("Car with serial number: %s already exists", serialNumber), HttpStatus.BAD_REQUEST);
    }
}
