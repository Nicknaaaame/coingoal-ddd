package ru.lapotko.coingoal.core.aggregates.valueobjects;

public record FiatCoinPercent(FiatAmount fiatAmount, CoinAmount coinAmount, PercentAmount percent) {
}
