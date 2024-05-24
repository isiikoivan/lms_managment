package com.example.lms_system.exception.validation;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Getter
public class ValidationExceptionMessage {
    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime timestamp;

    public ValidationExceptionMessage(String message, HttpStatus status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
