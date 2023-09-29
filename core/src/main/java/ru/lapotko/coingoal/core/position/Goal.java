package ru.lapotko.coingoal.core.position;

import ru.lapotko.coingoal.core.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;

record Goal (Long id, FiatAmount sellPrice, CoinAmount sellAmount) {
}
