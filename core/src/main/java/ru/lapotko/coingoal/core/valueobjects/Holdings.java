package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Holdings(BigDecimal fiatAmount, BigDecimal coinAmount) {
    public Holdings {
        if (fiatAmount == null)
            throw new DomainValidationException("Fiat amount should not be null");
        if (coinAmount == null)
            throw new DomainValidationException("Coin amount should not be null");

        fiatAmount = fiatAmount.setScale(10, RoundingMode.CEILING);
        coinAmount = coinAmount.setScale(10, RoundingMode.CEILING);
    }
}
