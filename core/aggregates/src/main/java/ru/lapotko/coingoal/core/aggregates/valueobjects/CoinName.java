package ru.lapotko.coingoal.core.aggregates.valueobjects;

public record CoinName(String name) {
    public CoinName {
        if (name == null)
            throw new IllegalArgumentException("Coin name cannot be null");
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Coin name cannot be empty");

        name = name.trim();
    }
}
