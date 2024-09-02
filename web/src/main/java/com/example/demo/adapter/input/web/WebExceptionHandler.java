package com.example.demo.adapter.input.web;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handle(IllegalStateException ex) {
        return errorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handle(MethodArgumentNotValidException ex) {
        return errorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handle(ConstraintViolationException ex) {
        return errorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handle(HttpMessageNotReadableException ex) {
        return errorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handle(Exception ex) {
        return errorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ErrorResponse errorResponse(Exception exception, HttpStatus badRequest) {
        return ErrorResponse.builder(exception, badRequest, exception.getMessage())
                .title(exception.getClass().getSimpleName())
                .build();
    }
}
