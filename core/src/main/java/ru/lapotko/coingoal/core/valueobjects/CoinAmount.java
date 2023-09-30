package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CoinAmount(BigDecimal amount) {
    public CoinAmount {
        if (amount == null)
            throw new DomainValidationException("Amount should not be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new DomainValidationException("Amount should be greater than zero");
        amount = amount.setScale(10, RoundingMode.CEILING);
    }
}
