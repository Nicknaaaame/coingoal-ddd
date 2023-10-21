package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

import java.util.Locale;

public record CoinSymbol(String symbol) {
    public CoinSymbol {
        if (symbol == null)
            throw new DomainValidationException("Coin symbol cannot be null");
        if (symbol.trim().isEmpty())
            throw new DomainValidationException("Coin symbol cannot be empty");
    }
}
