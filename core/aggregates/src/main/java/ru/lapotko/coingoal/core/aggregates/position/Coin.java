package ru.lapotko.coingoal.core.aggregates.position;

import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.aggregates.valueobjects.Change;
import ru.lapotko.coingoal.core.aggregates.valueobjects.CoinName;
import ru.lapotko.coingoal.core.aggregates.valueobjects.CoinSymbol;
import ru.lapotko.coingoal.core.aggregates.valueobjects.FiatAmount;

@RequiredArgsConstructor
class Coin {
    private final FiatAmount price;

    private final CoinName name;

    private final CoinSymbol symbol;

    private final Change change24h;
}
