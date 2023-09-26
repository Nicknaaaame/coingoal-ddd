package ru.lapotko.coingoal.core.aggregates.valueobjects;

public record Weight(Integer weight) {
    public Weight {
        if (weight == null)
            throw new IllegalArgumentException("Weight cannot be null");
        if (weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");
    }
}
