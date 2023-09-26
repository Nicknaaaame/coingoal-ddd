package ru.lapotko.coingoal.core.aggregates.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record PercentAmount(BigDecimal percent) {
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public PercentAmount {
        if (percent == null)
            throw new IllegalArgumentException("Percent should not be null");
        if (percent.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Percent should be greater than or equal to zero");
        if (percent.compareTo(HUNDRED) > 0)
            throw new IllegalArgumentException("Percent should be less than or equal to 100");

        percent = percent.setScale(5, RoundingMode.CEILING);
    }
}
