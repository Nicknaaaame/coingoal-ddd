package ru.lapotko.coingoal.core.position.request;

import java.math.BigDecimal;
import java.util.Optional;

public record PositionUpdate(Long positionId, Optional<BigDecimal> holdings, Optional<BigDecimal> avgBuyPrice) {
}
