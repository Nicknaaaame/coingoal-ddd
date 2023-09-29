package ru.lapotko.coingoal.application.rest.dto.value;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PnlValue {
    private BigDecimal percent;
    private BigDecimal value;

    public PnlValue(@NonNull BigDecimal main, @NonNull BigDecimal part) {
//        this.percent = MathUtil.percent(main, part);
        this.value = part;
    }
}
