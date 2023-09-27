package ru.lapotko.coingoal.core.aggregates.position;

import ru.lapotko.coingoal.core.aggregates.valueobjects.*;

record Goal(Long id, Weight weight, FiatPercent sellPrice, FiatCoinPercent sellAmount, FiatCoin holdingsRemain,
            Pnl pnl) {
}
