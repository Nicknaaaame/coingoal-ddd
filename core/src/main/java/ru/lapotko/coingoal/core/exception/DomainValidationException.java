package ru.lapotko.coingoal.core.exception;

public class DomainValidationException extends DomainCoreException {
    public DomainValidationException(String message) {
        super(message);
    }
}
