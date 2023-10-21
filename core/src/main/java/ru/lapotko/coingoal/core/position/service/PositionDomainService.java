package ru.lapotko.coingoal.core.position.service;

import lombok.RequiredArgsConstructor;
import ru.lapotko.coingoal.core.exception.PositionNotFoundException;
import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.Goal;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.repository.GoalDomainRepository;
import ru.lapotko.coingoal.core.position.repository.PositionDomainRepository;
import ru.lapotko.coingoal.core.position.request.GoalRequest;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.core.position.request.PositionUpdate;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class PositionDomainService {

    private final PositionDomainRepository positionDomainRepository;
    private final GoalDomainRepository goalDomainRepository;

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

    public PageInfo<PositionAggregate> getPositionPage(PositionDomainFilter filter, PageableInfo pageableInfo) {
        return positionDomainRepository.findAll(filter, pageableInfo);
    }

    public PositionAggregate getPosition(Long positionId) {
        return positionDomainRepository.findPositionById(positionId).orElseThrow(() -> {
            throw new PositionNotFoundException(positionId);
        });
    }

    public void deletePosition(Long positionId) {
        positionDomainRepository.deletePosition(positionId);
    }

    public Long addGoal(Long positionId, GoalRequest goalRequest) {
        PositionAggregate positionAggregate = getPosition(positionId);
        Goal goal = goalDomainRepository.createGoal(positionId, goalRequest);
        positionAggregate.addGoal(goal);
        positionDomainRepository.savePosition(positionAggregate);
        return goal.getId();
    }

    public void deleteGoal(Long positionId, Long goalId) {
        PositionAggregate positionAggregate = getPosition(positionId);
        positionAggregate.removeGoal(goalId);
        goalDomainRepository.deleteGoal(goalId);
        positionDomainRepository.savePosition(positionAggregate);
    }
}
