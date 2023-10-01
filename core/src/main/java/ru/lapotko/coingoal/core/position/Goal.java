package ru.lapotko.coingoal.core.position;

import lombok.Getter;
import ru.lapotko.coingoal.core.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;
import ru.lapotko.coingoal.core.valueobjects.Weight;

@Getter
public class Goal implements Comparable<Goal> {
    private final Long id;

    private final Weight weight;
    private FiatAmount sellPrice;
    private CoinAmount sellAmount;

    public Goal(Long id, Weight weight, FiatAmount sellPrice, CoinAmount sellAmount) {
        this.id = id;
        this.weight = weight;
        this.sellPrice = sellPrice;
        this.sellAmount = sellAmount;
    }

    void setSellPrice(FiatAmount sellPrice) {
        this.sellPrice = sellPrice;
    }

    void setSellAmount(CoinAmount sellAmount) {
        this.sellAmount = sellAmount;
    }

    @Override
    public int compareTo(Goal o) {
        return this.weight.weight().compareTo(o.weight.weight());
    }
}
