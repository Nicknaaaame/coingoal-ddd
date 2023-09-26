package ru.lapotko.coingoal.core.aggregates.valueobjects;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public record CoinAmount(BigDecimal amount) {
    public CoinAmount {
        if (amount == null)
            throw new IllegalArgumentException("Amount should not be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount should be greater than zero");
        amount = amount.setScale(10, RoundingMode.CEILING);
    }
}
