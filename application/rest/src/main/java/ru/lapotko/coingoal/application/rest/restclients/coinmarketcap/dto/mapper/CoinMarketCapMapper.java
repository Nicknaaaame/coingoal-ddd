package ru.lapotko.coingoal.application.rest.restclients.coinmarketcap.dto.mapper;

import ru.lapotko.coingoal.application.rest.dto.CoinDto;
import ru.lapotko.coingoal.application.rest.restclients.coinmarketcap.dto.CoinListing;

public class CoinMarketCapMapper {
    private static final String QUOTE_USD = "USD";

    public CoinDto map(CoinListing value) {
        return CoinDto.builder()
                .name(value.getName())
                .price(value.getQuote().get(QUOTE_USD).getPrice())
                .change24h(value.getQuote().get(QUOTE_USD).getPercentChange24h())
                .symbol(value.getSymbol())
                .build();
    }
}
