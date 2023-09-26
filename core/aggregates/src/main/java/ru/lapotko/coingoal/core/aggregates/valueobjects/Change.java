package ru.lapotko.coingoal.core.aggregates.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Change(BigDecimal change) {
    public Change {
        if (change == null)
            throw new IllegalArgumentException("Change cannot be null");

        change = change.setScale(5, RoundingMode.CEILING);
    }
}
