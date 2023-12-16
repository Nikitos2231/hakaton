package com.alibou.security.exception.handler;

import com.alibou.security.exception.GenericException;
import com.alibou.security.rest.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.alibou.security.util.ErrorCode.BASE_ERROR;
import static com.alibou.security.util.ErrorCode.TOKEN_PROBLEM;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        e.getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error(BASE_ERROR, "Invalid data", errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto<?>> handle(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error(BASE_ERROR, e.getMessage()));
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseDto<?>> handle(GenericException e) {
        return ResponseEntity.status(e.getStatus())
                .body(ResponseDto.error(TOKEN_PROBLEM, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handle(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error(e.getMessage()));
    }
}
