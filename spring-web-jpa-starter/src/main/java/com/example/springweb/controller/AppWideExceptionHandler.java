package com.example.springweb.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class AppWideExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setTitle("User Not Found");
        detail.setType(URI.create("http://localhost:8080/v1/errors/user-not-found"));
        LocalDateTime dateTime = LocalDateTime.now();
        detail.setProperty("timestamp", dateTime);
        return detail;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(status, "Bad Request");
        detail.setTitle("Validation Errors");
        detail.setType(URI.create("http://localhost:8080/v1/errors/uer-validation-errors"));
        LocalDateTime dateTime = LocalDateTime.now();
        detail.setProperty("timestamp", dateTime);
        detail.setProperty("MessageArgs", ex.getFieldErrors());
        return ResponseEntity.status(status).body(detail);
    }
}
