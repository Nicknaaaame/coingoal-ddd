package ru.lapotko.coingoal.core.aggregates.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record FiatAmount(BigDecimal price) {
    public FiatAmount {
        if (price == null)
            throw new IllegalArgumentException("Price should not be null");
        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price should be greater than zero");
        price = price.setScale(10, RoundingMode.CEILING);
    }
}
