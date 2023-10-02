package ru.lapotko.coingoal.core.position;

import ru.lapotko.coingoal.core.valueobjects.*;

public record CalculatedGoal(Long id, Weight weight, SellPrice sellPrice, SellAmount sellAmount,
                             Holdings holdingsRemain,
                             Pnl pnl) {
}
