package ru.lapotko.coingoal.application.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinResponse {
    private Long id;
    private String name;
    private String symbol;
}
