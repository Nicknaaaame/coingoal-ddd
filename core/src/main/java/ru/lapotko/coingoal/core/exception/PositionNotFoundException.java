package ru.lapotko.coingoal.core.exception;

public class PositionNotFoundException extends DomainNotFoundException {
    public PositionNotFoundException(Long id) {
        super("Position with id [%s] not found".formatted(id));
    }
}
