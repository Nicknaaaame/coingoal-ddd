package ru.lapotko.coingoal.core.position;

import lombok.Getter;
import ru.lapotko.coingoal.core.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;

@Getter
public class Goal {
    private final Long id;
    private FiatAmount sellPrice;
    private CoinAmount sellAmount;

    public Goal(Long id, FiatAmount sellPrice, CoinAmount sellAmount) {
        this.id = id;
        this.sellPrice = sellPrice;
        this.sellAmount = sellAmount;
    }

    void setSellPrice(FiatAmount sellPrice) {
        this.sellPrice = sellPrice;
    }

    void setSellAmount(CoinAmount sellAmount) {
        this.sellAmount = sellAmount;
    }
}
