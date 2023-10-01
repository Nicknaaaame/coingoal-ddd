package ru.lapotko.coingoal.application.rest.mapper;

import ru.lapotko.coingoal.application.rest.dto.CalculatedGoalDto;
import ru.lapotko.coingoal.application.rest.dto.CoinDto;
import ru.lapotko.coingoal.application.rest.dto.response.CoinResponse;
import ru.lapotko.coingoal.application.rest.dto.response.PositionResponse;
import ru.lapotko.coingoal.application.rest.dto.value.FiatCoinPercentValue;
import ru.lapotko.coingoal.application.rest.dto.value.FiatCoinValue;
import ru.lapotko.coingoal.application.rest.dto.value.FiatPercentValue;
import ru.lapotko.coingoal.application.rest.dto.value.PnlValue;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.valueobjects.Pnl;

import java.util.Optional;
import java.util.stream.Collectors;

public class RestMapper {
    public static PositionResponse toPositionResponse(PositionAggregate positionAggregate) {
        return PositionResponse.builder()
                .id(positionAggregate.getId())
                .avgBuyPrice(positionAggregate.getAvgBuyPrice().fiat())
                .coin(toCoinDto(positionAggregate.getCoin()))
                .goals(positionAggregate.calculateGoals().stream()
                        .map(goal -> CalculatedGoalDto.builder()
                                .id(goal.id())
                                .weight(goal.weight().weight())
                                .sellPrice(FiatPercentValue.builder()
                                        .fiatAmount(goal.sellPrice().fiatAmount().fiat())
                                        .percent(goal.sellPrice().percent().percent())
                                        .build())
                                .sellAmount(FiatCoinPercentValue.builder()
                                        .coinAmount(goal.sellAmount().coinAmount().amount())
                                        .fiatAmount(goal.sellAmount().fiatAmount().fiat())
                                        .percent(goal.sellAmount().percent().percent())
                                        .build())
                                .holdingsRemain(FiatCoinValue.builder()
                                        .coinAmount(goal.holdingsRemain().coinAmount().amount())
                                        .fiatAmount(goal.holdingsRemain().fiatAmount().fiat())
                                        .build())
                                .pnl(toPnlValue(Optional.ofNullable(goal.pnl())))
                                .build())
                        .collect(Collectors.toList()))
                .holdings(positionAggregate.currentHoldings().amount())
                .totalProfit(toPnlValue(positionAggregate.calculatePnl()))
                .build();
    }

    public static CoinDto toCoinDto(Coin coin) {
        return CoinDto.builder()
                .name(coin.name().name())
                .change24h(coin.change24h().change())
                .symbol(coin.symbol().symbol())
                .price(coin.price().fiat())
                .build();
    }

    public static CoinResponse toCoinResponse(Coin coin) {
        return CoinResponse.builder()
                .id(coin.id())
                .name(coin.name().name())
                .symbol(coin.symbol().symbol())
                .build();
    }

    public static PnlValue toPnlValue(Optional<Pnl> pnlOptional) {
        return pnlOptional.map(pnl -> PnlValue.builder()
                        .fiatAmount(pnl.fiatAmount().fiat())
                        .percent(pnl.percent().percent())
                        .build())
                .orElse(null);
    }
}
