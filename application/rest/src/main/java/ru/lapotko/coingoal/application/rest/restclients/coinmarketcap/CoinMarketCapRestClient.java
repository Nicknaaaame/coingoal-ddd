package ru.lapotko.coingoal.application.rest.restclients.coinmarketcap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.lapotko.coingoal.application.rest.restclients.ExternalCoinClient;
import ru.lapotko.coingoal.application.rest.restclients.coinmarketcap.config.CoinMarketCapConfig;
import ru.lapotko.coingoal.application.rest.dto.CoinDto;

import java.util.List;

@Service
public class CoinMarketCapRestClient implements ExternalCoinClient {

    private final WebClient client;
    private final CoinMarketCapConfig config;

    public CoinMarketCapRestClient(@Qualifier("coinMarketCapClient") WebClient client, CoinMarketCapConfig config) {
        this.client = client;
        this.config = config;
    }

    @Override
    public List<CoinDto> getCoins() {
        return client.get()
                .uri(config.getCoinListPath())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CoinDto>>() {
                })
                .block();
    }
}
