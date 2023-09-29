package ru.lapotko.coingoal.application.rest.exception;

import org.springframework.http.HttpStatus;

public class GoalNotFoundException extends CoinGoalApiException {
    private final Long id;

    public GoalNotFoundException(Long id, HttpStatus status) {
        super(status);
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Goal with id [%s] not found".formatted(id);
    }
}
