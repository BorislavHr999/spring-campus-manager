package com.campus.campus_management_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // Вземаме точно нашето съобщение ("Този имейл вече е регистриран!")
        String errorMessage = ex.getReason();
        
        // Връщаме го като чист текст заедно със статуса (напр. 400 Bad Request)
        return new ResponseEntity<>(errorMessage, ex.getStatusCode());
    }
}
