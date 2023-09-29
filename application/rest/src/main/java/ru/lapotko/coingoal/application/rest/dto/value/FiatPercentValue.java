package ru.lapotko.coingoal.application.rest.dto.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiatPercentValue {
    private BigDecimal fiatAmount;
    private BigDecimal percent;
}
