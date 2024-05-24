package com.example.lms_system.exception.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
