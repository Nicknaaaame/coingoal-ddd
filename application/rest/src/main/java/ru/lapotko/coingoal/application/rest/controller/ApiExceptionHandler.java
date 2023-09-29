package ru.lapotko.coingoal.application.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.lapotko.coingoal.application.rest.exception.CoinGoalApiException;
import ru.lapotko.coingoal.application.rest.dto.ErrorDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return new ResponseEntity<>(
                new ErrorDetails(new Date(), errors, HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoinGoalApiException.class)
    public ResponseEntity<?> handle(CoinGoalApiException exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(), List.of(exception.getMessage()), exception.getStatus()),
                exception.getStatus());
    }
}
