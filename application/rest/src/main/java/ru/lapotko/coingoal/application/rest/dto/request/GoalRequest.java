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
public class GoalRequest {
    @NotNull(message = "The positionId should not be empty")
    @Positive(message = "The positionId should be greater then zero")
    private Long positionId;

    @NotNull(message = "The sellAmount should not be empty")
    @Positive(message = "The sellAmount should be greater then zero")
    private BigDecimal sellAmount;

    @NotNull(message = "The sellPrice should not be empty")
    @Positive(message = "The sellPrice should be greater then zero")
    private BigDecimal sellPrice;
}
