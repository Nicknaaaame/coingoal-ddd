package ru.lapotko.coingoal.core.aggregates.valueobjects;

import java.util.Locale;

public record CoinSymbol(String symbol) {
    public CoinSymbol {
        if (symbol == null)
            throw new IllegalArgumentException("Coin symbol cannot be null");
        if (symbol.trim().isEmpty())
            throw new IllegalArgumentException("Coin symbol cannot be empty");
        if (!symbol.toUpperCase(Locale.ROOT).equals(symbol))
            throw new IllegalArgumentException("Symbol should be with upper case");
    }
}
