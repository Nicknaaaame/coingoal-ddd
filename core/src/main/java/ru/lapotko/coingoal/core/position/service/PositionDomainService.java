package ru.lapotko.coingoal.core.position.service;

import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.exception.PositionNotFoundException;
import ru.lapotko.coingoal.core.filtration.PositionFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.repository.CoinDomainRepository;
import ru.lapotko.coingoal.core.position.repository.PositionDomainRepository;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.core.position.request.PositionUpdate;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class PositionDomainService {

    private final PositionDomainRepository positionDomainRepository;

    public PositionAggregate createPosition(PositionCreate request) {
        return positionDomainRepository.createPosition(request);
    }

    public PositionAggregate updatePosition(PositionUpdate request) {
        PositionAggregate positionAggregate = getPosition(request.positionId());
        ofNullable(request.avgBuyPrice()).ifPresent(avgBuyPrice -> positionAggregate.updateAvgBuyPrice(avgBuyPrice.orElse(null)));
        ofNullable(request.holdings()).ifPresent(holdings -> positionAggregate.updateHoldings(holdings.orElse(null)));
        positionDomainRepository.savePosition(positionAggregate);
        return positionAggregate;
    }

    public PageInfo<PositionAggregate> getPositionPage(PositionFilterInfo filterInfo, PageableInfo pageableInfo) {
        return positionDomainRepository.findAll(filterInfo, pageableInfo);
    }

    public PositionAggregate getPosition(Long positionId) {
        return positionDomainRepository.findPositionById(positionId).orElseThrow(() -> {
            throw new PositionNotFoundException(positionId);
        });
    }

    public void deletePosition(Long positionId) {
        positionDomainRepository.deletePosition(positionId);
    }
}