package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record FiatAmount(BigDecimal fiat) {
    public FiatAmount {
        if (fiat == null)
            throw new DomainValidationException("Price should not be null");
        if (fiat.compareTo(BigDecimal.ZERO) <= 0)
            throw new DomainValidationException("Price should be greater than zero");
        fiat = fiat.setScale(10, RoundingMode.CEILING);
    }
}
