package ru.lapotko.coingoal.core.valueobjects;

public record SellAmount(FiatAmount fiatAmount, CoinAmount coinAmount, PercentAmount percent) {
}
