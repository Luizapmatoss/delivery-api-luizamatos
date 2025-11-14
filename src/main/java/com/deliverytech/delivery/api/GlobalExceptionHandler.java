package com.deliverytech.delivery.api;

import com.deliverytech.delivery.api.ErrorResponse;
import com.deliverytech.delivery.api.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse err = new ErrorResponse(
                404, ex.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // 400 – Erro de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
            errors.put(err.getField(), err.getDefaultMessage())
        );

        ValidationErrorResponse response = new ValidationErrorResponse(
                400,
                "Erro de validação",
                request.getDescription(false),
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    // 409 Conflict
    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(DataConflictException ex, WebRequest request) {
        ErrorResponse err = new ErrorResponse(409, ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(Exception ex, WebRequest request) {
        ErrorResponse err = new ErrorResponse(
                500,
                "Erro interno no servidor",
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
