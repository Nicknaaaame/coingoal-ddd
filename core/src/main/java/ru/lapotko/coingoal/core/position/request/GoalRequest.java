package ru.lapotko.coingoal.core.position.request;

import java.math.BigDecimal;

public record GoalRequest(BigDecimal sellAmount, BigDecimal sellPrice) {
}
