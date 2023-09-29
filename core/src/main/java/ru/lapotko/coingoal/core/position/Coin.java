package ru.lapotko.coingoal.core.position;

import ru.lapotko.coingoal.core.valueobjects.Change;
import ru.lapotko.coingoal.core.valueobjects.CoinName;
import ru.lapotko.coingoal.core.valueobjects.CoinSymbol;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;

public record Coin(Long id, FiatAmount price, CoinName name, CoinSymbol symbol, Change change24h) {
}
