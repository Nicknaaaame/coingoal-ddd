package ru.lapotko.coingoal.core.aggregates.position;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.aggregates.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.aggregates.valueobjects.FiatAmount;
import ru.lapotko.coingoal.core.aggregates.valueobjects.UserId;

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

    void addGoals(List<Goal> goals) {
        this.goals.addAll(goals);
    }
}
