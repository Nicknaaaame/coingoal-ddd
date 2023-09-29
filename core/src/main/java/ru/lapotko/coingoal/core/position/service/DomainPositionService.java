package ru.lapotko.coingoal.core.position.service;

import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.position.repository.CoinRepository;
import ru.lapotko.coingoal.core.position.repository.PositionRepository;
import ru.lapotko.coingoal.core.position.request.PositionRequest;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.PositionAggregate;

@RequiredArgsConstructor
public class DomainPositionService {

    private final PositionRepository positionRepository;
    private final CoinRepository coinRepository;

    public PositionAggregate createPosition(PositionRequest request) {
        PositionAggregate positionAggregate = new PositionAggregate.PositionBuilder()
                .id(request.id())
                .avgBuyPrice(request.avgBuyPrice())
                .holdings(request.holdings())
                .userId(request.userId())
                .coin(getCoin(request.coinId()))
                .build();
        positionRepository.savePosition(positionAggregate);
        return positionAggregate;
    }

    public PositionAggregate savePosition(PositionAggregate positionAggregate) {
        positionRepository.savePosition(positionAggregate);
        return positionAggregate;
    }

    public PositionAggregate getPosition(Long positionId) {
        return positionRepository.findPositionById(positionId).orElseThrow(() -> {
            throw new IllegalArgumentException("Position with id [%s] not found".formatted(positionId));
        });
    }

    private Coin getCoin(Long coinId) {
        return coinRepository.findCoinById(coinId).orElseThrow(() -> {
            throw new IllegalArgumentException("Coin with id [%s] not found".formatted(coinId));
        });
    }
}
