package ru.lapotko.coingoal.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapotko.coingoal.application.rest.dto.value.FiatCoinPercentValue;
import ru.lapotko.coingoal.application.rest.dto.value.FiatCoinValue;
import ru.lapotko.coingoal.application.rest.dto.value.FiatPercentValue;
import ru.lapotko.coingoal.application.rest.dto.value.PnlValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculatedGoalDto {
    private Long id;
    private Integer weight;
    private FiatPercentValue sellPrice;
    private FiatCoinPercentValue sellAmount;
    private FiatCoinValue holdingsRemain;
    private PnlValue pnl;
}
