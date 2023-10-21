package ru.lapotko.coingoal.infrastructure.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.request.GoalRequest;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.core.position.request.PositionUpdate;
import ru.lapotko.coingoal.core.position.service.PositionDomainService;
import ru.lapotko.coingoal.infrastructure.jpa.filter.PositionJpaFilter;

@Service
@RequiredArgsConstructor
public class PositionApplicationService {
    private final PositionDomainService positionDomainService;

    @Transactional(isolation = Isolation.DEFAULT)
    public PositionAggregate createPosition(PositionCreate request) {
        return positionDomainService.createPosition(request);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public PositionAggregate updatePosition(PositionUpdate request) {
        return positionDomainService.updatePosition(request);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PageInfo<PositionAggregate> getPositionPage(PositionDomainFilter filter, PageableInfo pageableInfo) {
        return positionDomainService.getPositionPage(filter, pageableInfo);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PositionAggregate getPosition(Long positionId) {
        return positionDomainService.getPosition(positionId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deletePosition(Long positionId) {
        positionDomainService.deletePosition(positionId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Long addGoal(Long positionId, GoalRequest goalRequest) {
        return positionDomainService.addGoal(positionId, goalRequest);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteGoal(Long positionId, Long goalId) {
        positionDomainService.deleteGoal(positionId, goalId);
    }

}
