package ru.lapotko.coingoal.core.exception;

public class DomainNotFoundException extends DomainCoreException {
    public DomainNotFoundException(String message) {
        super(message);
    }
}
