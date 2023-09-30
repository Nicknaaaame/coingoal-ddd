package ru.lapotko.coingoal.application.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.lapotko.coingoal.application.rest.dto.ErrorDetails;
import ru.lapotko.coingoal.core.exception.DomainCoreException;
import ru.lapotko.coingoal.core.exception.DomainNotFoundException;
import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleRequestValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return new ResponseEntity<>(
                new ErrorDetails(new Date(), errors, HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<?> handleDomainValidationException(DomainValidationException exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(), List.of(exception.getMessage()), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<?> handleDomainNotFoundException(DomainNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(), List.of(exception.getMessage()), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DomainCoreException.class)
    public ResponseEntity<?> handleDomainCoreException(DomainCoreException exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(), List.of(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
