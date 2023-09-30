package ru.lapotko.coingoal.application.rest.service;

import org.springframework.stereotype.Service;
import ru.lapotko.coingoal.application.rest.dto.response.PositionResponse;
import ru.lapotko.coingoal.core.position.PositionAggregate;

@Service
public class PositionService {
    public PositionResponse getPositionResponse(PositionAggregate positionAggregate) {
        return PositionResponse.builder()
                .id(positionAggregate.getId())
                .avgBuyPrice(positionAggregate.getAvgBuyPrice())
                .coin(positionAggregate.getCoin())
                .goals(positionAggregate.calculateGoals())
                .holdings(positionAggregate.currentHoldings())
                .totalProfit(positionAggregate.calculatePnl())
                .build();
    }
}
