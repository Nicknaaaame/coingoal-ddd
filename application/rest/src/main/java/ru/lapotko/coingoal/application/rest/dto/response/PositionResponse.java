package ru.lapotko.coingoal.application.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapotko.coingoal.core.position.CalculatedGoal;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.valueobjects.CoinAmount;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;
import ru.lapotko.coingoal.core.valueobjects.Pnl;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionResponse {
    private Long id;
    private Coin coin;
    private CoinAmount holdings;
    private FiatAmount avgBuyPrice;
    private List<CalculatedGoal> goals;
    private Pnl totalProfit;
}
