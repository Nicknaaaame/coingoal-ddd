package ru.lapotko.coingoal.core.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.lapotko.coingoal.core.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;
import ru.lapotko.coingoal.core.valueobjects.UserId;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
class PositionRootEntity {
    private final Long id;

    private final UserId userId;
    private final Coin coin;
    private final List<Goal> goals = new ArrayList<>();
    private CoinAmount holdings;
    private FiatAmount avgBuyPrice;

    void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    void addGoals(List<Goal> goals) {
        this.goals.addAll(goals);
    }

    public void setHoldings(CoinAmount holdings) {
        this.holdings = holdings;
    }

    public void setAvgBuyPrice(FiatAmount avgBuyPrice) {
        this.avgBuyPrice = avgBuyPrice;
    }
}
