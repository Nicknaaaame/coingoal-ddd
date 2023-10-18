package ru.lapotko.coingoal.application.graphql.mapper;

import ru.lapotko.coingoal.application.graphql.types.types.CoinGql;
import ru.lapotko.coingoal.core.position.Coin;

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
}
