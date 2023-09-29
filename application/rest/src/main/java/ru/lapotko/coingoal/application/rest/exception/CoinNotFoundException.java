package ru.lapotko.coingoal.application.rest.exception;

import org.springframework.http.HttpStatus;

public class CoinNotFoundException extends CoinGoalApiException {
    private final Long id;

    public CoinNotFoundException(Long id, HttpStatus status) {
        super(status);
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Coin with id [%s] not found".formatted(id);
    }
}
