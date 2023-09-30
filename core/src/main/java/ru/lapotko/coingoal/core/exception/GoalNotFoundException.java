package ru.lapotko.coingoal.core.exception;

public class GoalNotFoundException extends DomainNotFoundException {
    public GoalNotFoundException(Long id) {
        super("Goal with id [%s] not found".formatted(id));
    }
}
