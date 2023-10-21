package ru.lapotko.coingoal.application.graphql.mapper;

import ru.lapotko.coingoal.application.graphql.types.*;
import ru.lapotko.coingoal.core.filtration.CoinDomainFilter;
import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.core.filtration.StringFilterInfo;
import ru.lapotko.coingoal.core.pagination.OrderInfo;
import ru.lapotko.coingoal.core.pagination.SortDirectionInfo;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.valueobjects.Pnl;
import ru.lapotko.coingoal.infrastructure.jpa.filter.CoinJpaFilter;
import ru.lapotko.coingoal.infrastructure.jpa.filter.PositionJpaFilter;

import java.util.Locale;
import java.util.stream.Collectors;

public class GraphQlMapper {

    public static PositionType toPositionType(PositionAggregate position) {
        return PositionType.newBuilder()
                .id(String.valueOf(position.getId()))
                .avgBuyPrice(position.getAvgBuyPrice().fiat().doubleValue())
                .coin(toCoinType(position.getCoin()))
                .goals(position.calculateGoals().stream()
                        .map(goal -> CalculatedGoalType.newBuilder()
                                .id(String.valueOf(goal.id()))
                                .weight(goal.weight().weight())
                                .sellPrice(FiatPercentType.newBuilder()
                                        .fiatAmount(goal.sellPrice().fiatAmount().fiat().doubleValue())
                                        .percent(goal.sellPrice().percent().percent().doubleValue())
                                        .build())
                                .sellAmount(FiatCoinPercentType.newBuilder()
                                        .coinAmount(goal.sellAmount().coinAmount().amount().doubleValue())
                                        .fiatAmount(goal.sellAmount().fiatAmount().fiat().doubleValue())
                                        .percent(goal.sellAmount().percent().percent().doubleValue())
                                        .build())
                                .holdingsRemain(FiatCoinType.newBuilder()
                                        .coinAmount(goal.holdingsRemain().coinAmount().doubleValue())
                                        .fiatAmount(goal.holdingsRemain().fiatAmount().doubleValue())
                                        .build())
                                .pnl(toPnlType(goal.pnl()))
                                .build())
                        .collect(Collectors.toList()))
                .holdings(FiatCoinType.newBuilder()
                        .fiatAmount(position.currentHoldings().amount().multiply(position.getCoin().price().fiat()).doubleValue())
                        .coinAmount(position.currentHoldings().amount().doubleValue())
                        .build())
                .totalProfit(toPnlType(position.calculatePnl().orElse(null)))
                .build();
    }

    public static CoinType toCoinType(Coin coin) {
        return CoinType.newBuilder()
                .id(String.valueOf(coin.id()))
                .name(coin.name().name())
                .change24h(coin.change24h().change().doubleValue())
                .symbol(coin.symbol().symbol())
                .price(coin.price().fiat().doubleValue())
                .build();
    }

    public static PnlType toPnlType(Pnl pnl) {
        if (pnl == null)
            return null;
        return PnlType.newBuilder()
                .fiatAmount(pnl.fiatAmount().doubleValue())
                .percent(pnl.percent().doubleValue())
                .build();
    }

    public static PositionDomainFilter toPositionDomainFilter(PositionFilterInput filter) {
        if (filter == null)
            return PositionJpaFilter.empty();

        return new PositionJpaFilter(toCoinDomainFilter(filter.getCoinFilter()));
    }

    public static CoinDomainFilter toCoinDomainFilter(CoinFilterInput filter) {
        if (filter == null)
            return CoinJpaFilter.empty();
        return new CoinJpaFilter(
                StringFilterInfo.builder()
                        .cont(filter.getName().getCont())
                        .eq(filter.getName().getEq())
                        .build(),
                StringFilterInfo.builder()
                        .cont(filter.getSymbol().getCont())
                        .eq(filter.getSymbol().getEq())
                        .build());
    }

    public static OrderInfo toOrderInfo(CoinSortInput coinSortInput) {
        return new OrderInfo(
                coinSortInput.getField().name().toLowerCase(Locale.ROOT),
                toSortDirectionInfo(coinSortInput.getDirection()));
    }

    public static OrderInfo toOrderInfo(PositionSortInput positionSortInput) {
        return new OrderInfo(
                positionSortInput.getField().name().toLowerCase(Locale.ROOT),
                toSortDirectionInfo(positionSortInput.getDirection()));
    }

    public static SortDirectionInfo toSortDirectionInfo(SortDirection sortDirection) {
        return sortDirection.equals(SortDirection.ASC)
                ? SortDirectionInfo.ASC
                : SortDirectionInfo.DESC;
    }
}
