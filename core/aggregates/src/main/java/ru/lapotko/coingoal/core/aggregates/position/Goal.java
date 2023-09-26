package ru.lapotko.coingoal.core.aggregates.position;

import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.aggregates.valueobjects.*;

@RequiredArgsConstructor
class Goal {
    private final Long id;
    private final Weight weight;
    private final FiatPercent sellPrice;
    private final FiatCoinPercent sellAmount;
    private final FiatCoin holdingsRemain;
    private final Pnl pnl;
}
