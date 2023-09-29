package ru.lapotko.coingoal.application.rest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CoinGoalApiException extends RuntimeException {
    private final HttpStatus status;
}
