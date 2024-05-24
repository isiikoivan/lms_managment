package com.example.lms_system.exception;

import com.example.lms_system.exception.notfound.NotFoundException;
import com.example.lms_system.exception.notfound.NotFoundExceptionMessage;
import com.example.lms_system.exception.validation.ValidationException;
import com.example.lms_system.exception.validation.ValidationExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(NotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        NotFoundExceptionMessage exceptionMessage = new NotFoundExceptionMessage(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionMessage,notFound);
    }
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> handleInvalidInput(ValidationException e){
        HttpStatus unacceptable =HttpStatus.NOT_ACCEPTABLE;
        ValidationExceptionMessage invalid = new ValidationExceptionMessage(
                e.getMessage(),
                unacceptable,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return  new ResponseEntity<>(invalid,unacceptable);
    }
}
