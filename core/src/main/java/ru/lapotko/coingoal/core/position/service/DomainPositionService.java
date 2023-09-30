package ru.lapotko.coingoal.core.position.service;

import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.exception.CoinNotFoundException;
import ru.lapotko.coingoal.core.exception.PositionNotFoundException;
import ru.lapotko.coingoal.core.filtration.PositionFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.repository.CoinRepository;
import ru.lapotko.coingoal.core.position.repository.PositionRepository;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.request.PositionUpdate;

import javax.swing.text.Position;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class DomainPositionService {

    private final PositionRepository positionRepository;
    private final CoinRepository coinRepository;

    public PositionAggregate createPosition(PositionCreate request) {
        return positionRepository.createPosition(request);
    }

    public PositionAggregate updatePosition(PositionUpdate request) {
        PositionAggregate positionAggregate = getPosition(request.positionId());
        ofNullable(request.avgBuyPrice()).ifPresent(avgBuyPrice -> positionAggregate.updateAvgBuyPrice(avgBuyPrice.orElse(null)));
        ofNullable(request.holdings()).ifPresent(holdings -> positionAggregate.updateHoldings(holdings.orElse(null)));
        positionRepository.savePosition(positionAggregate);
        return positionAggregate;
    }

    public PageInfo<PositionAggregate> getPositionPage(PositionFilterInfo filterInfo, PageableInfo pageableInfo) {
        return positionRepository.findAll(filterInfo, pageableInfo);
    }

    public PositionAggregate getPosition(Long positionId) {
        return positionRepository.findPositionById(positionId).orElseThrow(() -> {
            throw new PositionNotFoundException(positionId);
        });
    }

    public void deletePosition(Long positionId) {
        positionRepository.deletePosition(positionId);
    }

    private Coin getCoin(Long coinId) {
        return coinRepository.findCoinById(coinId).orElseThrow(() -> {
            throw new CoinNotFoundException(coinId);
        });
    }
}
