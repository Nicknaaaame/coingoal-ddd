package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

public record Weight(Integer weight) {
    public Weight {
        if (weight == null)
            throw new DomainValidationException("Weight cannot be null");
        if (weight < 0)
            throw new DomainValidationException("Weight cannot be negative");
    }
}
