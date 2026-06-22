package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlantException.class)
    public ResponseEntity<PlantErrorResponse> handlePlantException(PlantException exception) {
        log.error(exception.getMessage(), exception);
        return buildResponse(exception.getStatus(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PlantErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        return buildResponse(HttpStatus.BAD_REQUEST, "Required fields are missing or invalid.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PlantErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred.");
    }

    private ResponseEntity<PlantErrorResponse> buildResponse(HttpStatus status, String message) {
        PlantErrorResponse response = new PlantErrorResponse(status.value(), message, LocalDateTime.now());
        return new ResponseEntity<>(response, status);
    }
}
