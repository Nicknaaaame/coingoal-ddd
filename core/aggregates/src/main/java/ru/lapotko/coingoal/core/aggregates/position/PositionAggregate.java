package ru.lapotko.coingoal.core.aggregates.position;

import lombok.Getter;
import lombok.NonNull;
import ru.lapotko.coingoal.core.aggregates.valueobjects.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PositionAggregate {
    @Getter
    private final PositionRootEntity position;

    private PositionAggregate(PositionBuilder builder) {
        this.position = new PositionRootEntity(
                builder.id,
                builder.userId,
                builder.holdings,
                builder.avgBuyPrice,
                builder.coin
        );
        this.position.addGoals(calculateGoals(builder.goals));
    }

    private List<Goal> calculateGoals(List<GoalRequest> goalRequests) {
        List<Goal> result = new ArrayList<>();
        BigDecimal holdingsRemain = position.getHoldings().amount();
        for (GoalRequest goal : goalRequests) {
            Integer weight = 0;
            Weight weightValue = new Weight(weight);

            FiatAmount sellPriceFiat = new FiatAmount(goal.sellPrice);
            PercentAmount sellPricePercent = new PercentAmount(priceMovePercent(position.getAvgBuyPrice().price(), goal.sellPrice));
            FiatPercent sellPriceValue = new FiatPercent(sellPriceFiat, sellPricePercent);

            FiatAmount sellAmountFiat = new FiatAmount(goal.sellPrice.multiply(goal.sellAmount));
            CoinAmount sellAmountCoin = new CoinAmount(goal.sellAmount);
            PercentAmount sellPercent = new PercentAmount(percent(holdingsRemain, goal.sellAmount));
            FiatCoinPercent sellAmountValue = new FiatCoinPercent(sellAmountFiat, sellAmountCoin, sellPercent);

            FiatAmount holdingsRemainFiat = new FiatAmount(holdingsRemain.multiply(goal.sellPrice));
            CoinAmount holdingsRemainCoin = new CoinAmount(holdingsRemain.subtract(goal.sellAmount));
            FiatCoin holdingsRemainValue = new FiatCoin(holdingsRemainFiat, holdingsRemainCoin);

            FiatAmount pnlFiat = new FiatAmount(position.getAvgBuyPrice().price().multiply(goal.sellAmount));
            PercentAmount pnlPercent = new PercentAmount(pnl(position.getAvgBuyPrice().price(), goal.sellPrice, goal.sellAmount));
            Pnl pnlValue = new Pnl(pnlFiat, pnlPercent);

            result.add(new Goal(
                    goal.id,
                    weightValue,
                    sellPriceValue,
                    sellAmountValue,
                    holdingsRemainValue,
                    pnlValue
            ));

            holdingsRemain = holdingsRemain.subtract(goal.sellAmount);
        }
        return result;
    }

    private BigDecimal pnl(BigDecimal entryPrice, BigDecimal exitPrice, BigDecimal amount) {
        return exitPrice.subtract(entryPrice).multiply(amount);
    }

    private BigDecimal percent(@NonNull BigDecimal main, BigDecimal part) {
        assert !BigDecimal.ZERO.equals(main);
        return part.multiply(BigDecimal.valueOf(100)).divide(main, RoundingMode.CEILING);
    }

    private BigDecimal priceMovePercent(BigDecimal entryPrice, BigDecimal exitPrice) {
        assert !BigDecimal.ZERO.equals(entryPrice);
        return exitPrice.subtract(entryPrice).divide(entryPrice, RoundingMode.CEILING).multiply(BigDecimal.valueOf(100));
    }

    public static class PositionBuilder {
        private Long id;
        private UserId userId;
        private CoinAmount holdings;
        private FiatAmount avgBuyPrice;
        private Coin coin;
        private final List<GoalRequest> goals = new ArrayList<>();

        public PositionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PositionBuilder userId(String userId) {
            this.userId = new UserId(userId);
            return this;
        }

        public PositionBuilder holdings(BigDecimal holdings) {
            this.holdings = new CoinAmount(holdings);
            return this;
        }

        public PositionBuilder avgBuyPrice(BigDecimal avgBuyPrice) {
            this.avgBuyPrice = new FiatAmount(avgBuyPrice);
            return this;
        }

        public PositionBuilder withCoin(BigDecimal price, String name, String symbol, BigDecimal change24h) {
            this.coin = new Coin(
                    new FiatAmount(price),
                    new CoinName(name),
                    new CoinSymbol(symbol),
                    new Change(change24h)
            );
            return this;
        }

        public PositionBuilder withGoal(GoalRequest goal) {
            this.goals.add(goal);
            return this;
        }

        public PositionAggregate build() {
            if (coin == null)
                throw new IllegalStateException("Coin cannot be null");
            if (avgBuyPrice == null)
                throw new IllegalStateException("Avg buy price cannot be null");
            return new PositionAggregate(this);
        }
    }

    public record GoalRequest(Long id, BigDecimal sellPrice, BigDecimal sellAmount) {
    }
}
