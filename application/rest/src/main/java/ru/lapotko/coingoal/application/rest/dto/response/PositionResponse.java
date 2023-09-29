package ru.lapotko.coingoal.application.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapotko.coingoal.application.rest.dto.value.FiatCoinValue;
import ru.lapotko.coingoal.application.rest.dto.value.PnlValue;
import ru.lapotko.coingoal.infrastructure.jpa.dto.CoinDto;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionResponse {
    private Long id;
    private CoinDto coin;
    private FiatCoinValue holdings;
    private BigDecimal avgBuyPrice;
    private List<GoalDto> goals;
    private PnlValue totalProfit;
}
