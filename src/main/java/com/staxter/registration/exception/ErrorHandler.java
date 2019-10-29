package com.staxter.registration.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex,
            WebRequest request) {


        Map<String, String> errors = new HashMap<>();
        errors.put("code", "USER_ALREADY_EXISTS");
        errors.put("description", ex.getMessage());
        return handleExceptionInternal(ex, errors,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
