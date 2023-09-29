package ru.lapotko.coingoal.application.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionRequest {
    @NotNull(message = "The coinId should not be empty")
    @Positive(message = "The coinId should be greater then zero")
    private Long coinId;

    @NotNull(message = "The holdings should not be empty")
    @Positive(message = "The holdings should be greater then zero")
    private BigDecimal holdings;

    @NotNull(message = "The avgBuyPrice should not be empty")
    @Positive(message = "The avgBuyPrice should be greater then zero")
    private BigDecimal avgBuyPrice;
}
