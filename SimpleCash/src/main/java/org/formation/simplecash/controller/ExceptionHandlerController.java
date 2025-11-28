package org.formation.simplecash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .filter(fieldError -> fieldError.getDefaultMessage() != null && !fieldError.getDefaultMessage().isEmpty())
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1
                ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException e) {
        return Map.of("error", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public Map<String, String> handleIllegalState(IllegalStateException e) {
        return Map.of("error", e.getMessage());
    }
}
