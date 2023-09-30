package ru.lapotko.coingoal.core.position.request;

import java.math.BigDecimal;

public record PositionCreate(String userId, Long coinId, BigDecimal holdings, BigDecimal avgBuyPrice) {
}
