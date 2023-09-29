package ru.lapotko.coingoal.application.rest.restclients.coinmarketcap.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Getter
public class CoinMarketCapConfig {
    @Value("${external.service.coin-market-cap.host}")
    private String host;
    @Value("${external.service.coin-market-cap.coin-list-path}")
    private String coinListPath;
    @Value("${external.service.coin-market-cap.api-key-header}")
    private String apiKeyHeader;
    @Value("${external.service.coin-market-cap.api-key}")
    private String apiKey;

    @Bean
    public WebClient coinMarketCapClient() {
        return WebClient.builder()
                .baseUrl(host)
                .defaultHeader(apiKeyHeader, apiKey)
                .build();
    }
}
