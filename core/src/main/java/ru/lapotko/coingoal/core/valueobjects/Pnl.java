package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Pnl(BigDecimal fiatAmount, BigDecimal percent) {
    public Pnl {
        if (fiatAmount == null)
            throw new DomainValidationException("Fiat amount should not be null");
        if (percent == null)
            throw new DomainValidationException("Coin amount should not be null");
        fiatAmount = fiatAmount.setScale(10, RoundingMode.CEILING);
        percent = percent.setScale(5, RoundingMode.CEILING);
    }
}
