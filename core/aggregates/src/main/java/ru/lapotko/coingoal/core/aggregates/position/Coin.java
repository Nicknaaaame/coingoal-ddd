package ru.lapotko.coingoal.core.aggregates.position;

import ru.lapotko.coingoal.core.aggregates.valueobjects.Change;
import ru.lapotko.coingoal.core.aggregates.valueobjects.CoinName;
import ru.lapotko.coingoal.core.aggregates.valueobjects.CoinSymbol;
import ru.lapotko.coingoal.core.aggregates.valueobjects.FiatAmount;

record Coin(FiatAmount price, CoinName name, CoinSymbol symbol, Change change24h) {
}
