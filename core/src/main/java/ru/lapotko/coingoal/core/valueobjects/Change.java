package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Change(BigDecimal change) {
    public Change {
        if (change == null)
            throw new DomainValidationException("Change cannot be null");

        change = change.setScale(5, RoundingMode.CEILING);
    }
}
