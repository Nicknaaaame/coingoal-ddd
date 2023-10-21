package ru.lapotko.coingoal.application.graphql.mapper;

import ru.lapotko.coingoal.application.graphql.types.CoinGql;
import ru.lapotko.coingoal.application.graphql.types.CoinSortInput;
import ru.lapotko.coingoal.application.graphql.types.SortDirection;
import ru.lapotko.coingoal.core.pagination.OrderInfo;
import ru.lapotko.coingoal.core.pagination.SortDirectionInfo;
import ru.lapotko.coingoal.core.position.Coin;

import java.util.Locale;

public class GraphQlMapper {
    public static CoinGql toCoinGql(Coin coin) {
        return CoinGql.newBuilder()
                .id(String.valueOf(coin.id()))
                .name(coin.name().name())
                .change24h(coin.change24h().change().doubleValue())
                .symbol(coin.symbol().symbol())
                .price(coin.price().fiat().doubleValue())
                .build();
    }

    public static OrderInfo toOrderInfo(CoinSortInput coinSortInput) {
        return new OrderInfo(
                coinSortInput.getField().name().toLowerCase(Locale.ROOT),
                coinSortInput.getDirection().equals(SortDirection.ASC)
                        ? SortDirectionInfo.ASC
                        : SortDirectionInfo.DESC);
    }
}
