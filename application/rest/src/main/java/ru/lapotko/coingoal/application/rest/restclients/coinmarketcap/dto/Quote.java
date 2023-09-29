package ru.lapotko.coingoal.application.rest.restclients.coinmarketcap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quote {
    private BigDecimal price;
    @JsonProperty("percent_change_24h")
    private BigDecimal percentChange24h;
}
