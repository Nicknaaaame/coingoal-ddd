package ru.lapotko.coingoal.application.rest.restclients.coinmarketcap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinListing {
    private long id;
    private String name;
    private String symbol;
    private Map<String, Quote> quote;
}
