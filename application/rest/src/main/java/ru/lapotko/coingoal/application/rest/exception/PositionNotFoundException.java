package ru.lapotko.coingoal.application.rest.exception;

import org.springframework.http.HttpStatus;

public class PositionNotFoundException extends CoinGoalApiException {
    private final Long id;

    public PositionNotFoundException(Long id, HttpStatus status) {
        super(status);
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Position with id [%s] not found".formatted(id);
    }
}
