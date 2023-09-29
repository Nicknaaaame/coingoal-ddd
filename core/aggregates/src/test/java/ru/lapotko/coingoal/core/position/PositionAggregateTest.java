package ru.lapotko.coingoal.core.position;

import org.junit.jupiter.api.Test;
import ru.lapotko.coingoal.core.valueobjects.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PositionAggregateTest {
    @Test
    public void shouldCalculateWithOneGoal() {
        PositionAggregate positionAggregate = new PositionAggregate.PositionBuilder()
                .id(1L)
                .avgBuyPrice(BigDecimal.valueOf(100))
                .holdings(BigDecimal.valueOf(100))
                .userId("USER_TEST")
                .withCoin(
                        BigDecimal.valueOf(100),
                        "Bitcoin",
                        "BTC",
                        BigDecimal.valueOf(1.1))
                .withGoal(new PositionAggregate.GoalRequest(
                        1L,
                        BigDecimal.valueOf(110),
                        BigDecimal.valueOf(1)))
                .build();
        Goal goal = positionAggregate.calculatePosition().getGoals().get(0);
        Weight expectedWeight = new Weight(0);
        FiatPercent expectedSellPrice = new FiatPercent(
                new FiatAmount(BigDecimal.valueOf(110)),
                new PercentAmount(BigDecimal.valueOf(10)));
        FiatCoinPercent expectedSellAmount = new FiatCoinPercent(
                new FiatAmount(BigDecimal.valueOf(110)),
                new CoinAmount(BigDecimal.valueOf(1)),
                new PercentAmount(BigDecimal.valueOf(1)));
        FiatCoin expectedHoldingsRemain = new FiatCoin(
                new FiatAmount(BigDecimal.valueOf(10890)),
                new CoinAmount(BigDecimal.valueOf(99)));
        Pnl expectedPnl = new Pnl(
                new FiatAmount(BigDecimal.valueOf(10)),
                new PercentAmount(BigDecimal.valueOf(10)));

        assertEquals(expectedWeight, goal.weight());
        assertEquals(expectedSellPrice, goal.sellPrice());
        assertEquals(expectedSellAmount, goal.sellAmount());
        assertEquals(expectedHoldingsRemain, goal.holdingsRemain());
        assertEquals(expectedPnl, goal.pnl());
    }

    @Test
    void shouldCorrectCalculateSecondGoal() {
        PositionAggregate positionAggregate = new PositionAggregate.PositionBuilder()
                .id(1L)
                .avgBuyPrice(BigDecimal.valueOf(100))
                .holdings(BigDecimal.valueOf(100))
                .userId("USER_TEST")
                .withCoin(
                        BigDecimal.valueOf(100),
                        "Bitcoin",
                        "BTC",
                        BigDecimal.valueOf(1.1))
                .withGoal(new PositionAggregate.GoalRequest(
                        1L,
                        BigDecimal.valueOf(110),
                        BigDecimal.valueOf(1)))
                .withGoal(new PositionAggregate.GoalRequest(
                        2L,
                        BigDecimal.valueOf(120),
                        BigDecimal.valueOf(2)))
                .build();

        Goal goal = positionAggregate.calculatePosition().getGoals().get(1);
        Weight expectedWeight = new Weight(1);
        FiatPercent expectedSellPrice = new FiatPercent(
                new FiatAmount(BigDecimal.valueOf(120)),
                new PercentAmount(BigDecimal.valueOf(20)));
        FiatCoinPercent expectedSellAmount = new FiatCoinPercent(
                new FiatAmount(BigDecimal.valueOf(240)),
                new CoinAmount(BigDecimal.valueOf(2)),
                new PercentAmount(BigDecimal.valueOf(3)));
        FiatCoin expectedHoldingsRemain = new FiatCoin(
                new FiatAmount(BigDecimal.valueOf(11640)),
                new CoinAmount(BigDecimal.valueOf(97)));
        Pnl expectedPnl = new Pnl(
                new FiatAmount(BigDecimal.valueOf(40)),
                new PercentAmount(BigDecimal.valueOf(40)));

        assertEquals(expectedWeight, goal.weight());
        assertEquals(expectedSellPrice, goal.sellPrice());
        assertEquals(expectedSellAmount, goal.sellAmount());
        assertEquals(expectedHoldingsRemain, goal.holdingsRemain());
        assertEquals(expectedPnl, goal.pnl());
    }
}
