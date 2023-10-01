package ru.lapotko.coingoal.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinDto {
    private BigDecimal price;
    private String name;
    private String symbol;
    private BigDecimal change24h;
}
