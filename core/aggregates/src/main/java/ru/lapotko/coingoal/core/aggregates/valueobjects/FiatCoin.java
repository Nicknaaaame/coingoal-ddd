package ru.lapotko.coingoal.core.aggregates.valueobjects;

public record FiatCoin(FiatAmount fiatAmount, CoinAmount coinAmount) {
}
