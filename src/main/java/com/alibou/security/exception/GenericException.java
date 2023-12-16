package com.alibou.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class GenericException extends RuntimeException {

    protected HttpStatus status;

    public GenericException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
