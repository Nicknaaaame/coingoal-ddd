package ru.lapotko.coingoal.core.position;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;
import ru.lapotko.coingoal.core.valueobjects.UserId;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
class PositionRootEntity {
    private final Long id;

    private final UserId userId;

    private final CoinAmount holdings;

    private final FiatAmount avgBuyPrice;

    private final Coin coin;

    private final List<Goal> goals = new ArrayList<>();

    void addGoal(Goal goal) {
        this.goals.add(goal);
    }
    void addGoals(List<Goal> goals) {
        this.goals.addAll(goals);
    }
}
