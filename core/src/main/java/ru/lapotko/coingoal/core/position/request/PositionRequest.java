package ru.lapotko.coingoal.core.position.request;

import java.math.BigDecimal;

public record PositionRequest(String userId, Long coinId, BigDecimal holdings, BigDecimal avgBuyPrice) {
}
