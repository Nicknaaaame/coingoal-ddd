package ru.lapotko.coingoal.core.valueobjects;

import ru.lapotko.coingoal.core.exception.DomainValidationException;

public record CoinName(String name) {
    public CoinName {
        if (name == null)
            throw new DomainValidationException("Coin name cannot be null");
        if (name.trim().isEmpty())
            throw new DomainValidationException("Coin name cannot be empty");

        name = name.trim();
    }
}
