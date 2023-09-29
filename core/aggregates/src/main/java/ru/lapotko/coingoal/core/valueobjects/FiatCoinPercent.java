package ru.lapotko.coingoal.core.valueobjects;

public record FiatCoinPercent(FiatAmount fiatAmount, CoinAmount coinAmount, PercentAmount percent) {
}
