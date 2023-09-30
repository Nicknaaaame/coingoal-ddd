package ru.lapotko.coingoal.core.position;

import lombok.NonNull;
import ru.lapotko.coingoal.core.exception.GoalNotFoundException;
import ru.lapotko.coingoal.core.valueobjects.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionAggregate {
    private final PositionRootEntity position;

    private transient List<CalculatedGoal> cachedCalculatedGoals;

    private PositionAggregate(PositionBuilder builder) {
        this.position = new PositionRootEntity(
                builder.id,
                builder.userId,
                builder.holdings,
                builder.avgBuyPrice,
                builder.coin
        );
        this.position.addGoals(builder.goals);
    }

    public Long getId() {
        return position.getId();
    }

    public UserId getUserId() {
        return position.getUserId();
    }

    public CoinAmount currentHoldings() {
        return position.getHoldings();
    }

    public FiatAmount getAvgBuyPrice() {
        return position.getAvgBuyPrice();
    }

    public Coin getCoin() {
        return position.getCoin();
    }

    public void addGoal(Goal goal) {
        this.position.addGoal(goal);
        cachedCalculatedGoals = null;
    }

    public void removeGoal(Long goalId) {
        this.position.getGoals().remove(getGoal(goalId));
        cachedCalculatedGoals = null;
    }

    public void updateGoal(Goal goal) {
        Goal updated = getGoal(goal.getId());
        updated.setSellAmount(goal.getSellAmount());
        updated.setSellPrice(goal.getSellPrice());
        cachedCalculatedGoals = null;
    }

    private Goal getGoal(Long goalId) {
        return this.position.getGoals().stream()
                .filter(goal -> goal.getId().equals(goalId))
                .findFirst()
                .orElseThrow(() -> {
                    throw new GoalNotFoundException(goalId);
                });
    }

    public List<CalculatedGoal> calculateGoals() {
        if (cachedCalculatedGoals != null)
            return cachedCalculatedGoals;

        List<CalculatedGoal> result = new ArrayList<>();
        BigDecimal holdingsRemain = position.getHoldings().amount();
        int weight = 0;
        for (Goal goal : this.position.getGoals()) {
            BigDecimal sellPrice = goal.getSellPrice().fiat();
            BigDecimal sellAmount = goal.getSellAmount().amount();
            Weight weightValue = new Weight(weight++);

            FiatAmount sellPriceFiat = new FiatAmount(sellPrice);
            PercentAmount sellPricePercent = new PercentAmount(priceMovePercent(position.getAvgBuyPrice().fiat(), sellPrice));
            FiatPercent sellPriceValue = new FiatPercent(sellPriceFiat, sellPricePercent);

            FiatAmount sellAmountFiat = new FiatAmount(sellPrice.multiply(sellAmount));
            CoinAmount sellAmountCoin = new CoinAmount(sellAmount);
            PercentAmount sellPercent = new PercentAmount(percent(position.getHoldings().amount(), sellAmount));
            FiatCoinPercent sellAmountValue = new FiatCoinPercent(sellAmountFiat, sellAmountCoin, sellPercent);

            FiatAmount holdingsRemainFiat = new FiatAmount(holdingsRemain.subtract(sellAmount).multiply(sellPrice));
            CoinAmount holdingsRemainCoin = new CoinAmount(holdingsRemain.subtract(sellAmount));
            FiatCoin holdingsRemainValue = new FiatCoin(holdingsRemainFiat, holdingsRemainCoin);

            BigDecimal difference = sellPrice.subtract(position.getAvgBuyPrice().fiat());
            FiatAmount pnlFiat = new FiatAmount(difference.multiply(sellAmount));
            PercentAmount pnlPercent = new PercentAmount(pnl(position.getAvgBuyPrice().fiat(), sellPrice, sellAmount));
            Pnl pnlValue = new Pnl(pnlFiat, pnlPercent);

            result.add(new CalculatedGoal(
                    goal.getId(),
                    weightValue,
                    sellPriceValue,
                    sellAmountValue,
                    holdingsRemainValue,
                    pnlValue
            ));

            holdingsRemain = holdingsRemain.subtract(sellAmount);
        }
        cachedCalculatedGoals = result;
        return result;
    }

    public Pnl calculatePnl() {
        if (cachedCalculatedGoals == null)
            cachedCalculatedGoals = calculateGoals();
        Optional<BigDecimal> fiatSum = cachedCalculatedGoals.stream()
                .map(goal -> goal.pnl().fiatAmount().fiat())
                .reduce(BigDecimal::add);

        Optional<BigDecimal> percentSum = cachedCalculatedGoals.stream()
                .map(goal -> goal.pnl().percent().percent())
                .reduce(BigDecimal::add);

        return fiatSum.map(bigDecimal -> new Pnl(
                        new FiatAmount(bigDecimal),
                        new PercentAmount(percentSum.get())))
                .orElse(null);

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
        private final List<Goal> goals = new ArrayList<>();

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

        public PositionBuilder coin(Coin coin) {
            this.coin = coin;
            return this;
        }

        public PositionBuilder withCoin(Long id, BigDecimal price, String name, String symbol, BigDecimal change24h) {
            this.coin = new Coin(
                    id,
                    new FiatAmount(price),
                    new CoinName(name),
                    new CoinSymbol(symbol),
                    new Change(change24h)
            );
            return this;
        }

        public PositionBuilder withGoal(Goal goal) {
            this.goals.add(goal);
            return this;
        }

        public PositionAggregate build() {
            if (coin == null)
                throw new IllegalStateException("Coin cannot be null");
            if (avgBuyPrice == null)
                throw new IllegalStateException("Avg buy fiat cannot be null");
            return new PositionAggregate(this);
        }
    }
}
