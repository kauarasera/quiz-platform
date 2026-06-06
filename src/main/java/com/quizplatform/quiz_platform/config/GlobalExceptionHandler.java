package com.quizplatform.quiz_platform.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());

        if (ex.getMessage().contains("not found")) {
            error.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        if (ex.getMessage().contains("already exists") ||
                ex.getMessage().contains("already registered")) {
            error.put("status", 409);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        error.put("status", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}