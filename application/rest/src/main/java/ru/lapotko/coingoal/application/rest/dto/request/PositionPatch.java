package ru.lapotko.coingoal.application.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionPatch {
    @NotNull(message = "The positionId should not be empty")
    @Positive(message = "The positionId should be greater then zero")
    private Long positionId;

    private Optional<
            @NotNull(message = "The holdings should not be empty")
            @Positive(message = "The holdings should be greater then zero")
                    BigDecimal> holdings;

    private Optional<
            @NotNull(message = "The avgBuyPrice should not be empty")
            @Positive(message = "The avgBuyPrice should be greater then zero")
                    BigDecimal> avgBuyPrice;
}
