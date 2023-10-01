package ru.lapotko.coingoal.application.rest.dto.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PnlValue {
    private BigDecimal percent;
    private BigDecimal fiatAmount;
}
