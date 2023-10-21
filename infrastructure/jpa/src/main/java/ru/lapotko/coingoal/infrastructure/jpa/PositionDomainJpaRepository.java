package ru.lapotko.coingoal.infrastructure.jpa;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.Goal;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.repository.PositionDomainRepository;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.entity.PositionEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.GoalEntityRepository;
import ru.lapotko.coingoal.infrastructure.jpa.repository.PositionEntityRepository;
import ru.lapotko.coingoal.infrastructure.jpa.service.CoinEntityService;
import ru.lapotko.coingoal.infrastructure.jpa.service.PositionEntityService;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PositionDomainJpaRepository implements PositionDomainRepository {

    private final CoinEntityService coinEntityService;
    private final PositionEntityService positionEntityService;
    private final PositionEntityRepository positionEntityRepository;
    private final GoalEntityRepository goalEntityRepository;

    @Override
    public PositionAggregate createPosition(PositionCreate positionRequest) {
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setUserId("ADMIN_USER");
        positionEntity.setAvgBuyPrice(positionRequest.avgBuyPrice());
        positionEntity.setHoldings(positionRequest.holdings());
        CoinEntity coin = coinEntityService.getCoin(positionRequest.coinId());
        positionEntity.setCoin(coin);

        positionEntityService.savePosition(positionEntity);

        return positionEntity.toDomain();
    }

    @Override
    public void savePosition(PositionAggregate positionAggregate) {
        PositionEntity positionEntity = positionEntityService.getPosition(positionAggregate.getId());
        positionEntity.setUserId("ADMIN_USER");
        positionEntity.setAvgBuyPrice(positionAggregate.getAvgBuyPrice().fiat());
        positionEntity.setHoldings(positionAggregate.currentHoldings().amount());
        positionEntity.setCoin(coinEntityService.getCoin(positionAggregate.getCoin().id()));

        List<Long> goalIds = positionAggregate.getGoalDefinitions().stream().map(Goal::getId).toList();

        positionEntity.setGoals(goalEntityRepository.findAllById(goalIds));
        positionEntityService.savePosition(positionEntity);
    }

    @Override
    public Optional<PositionAggregate> findPositionById(Long id) {
        return positionEntityRepository.findById(id).map(PositionEntity::toDomain);
    }

    @Override
    public PageInfo<PositionAggregate> findAll(PositionDomainFilter filter, PageableInfo pageableInfo) {
        Pageable pageable = ConvertUtil.convertToPageable(pageableInfo);
        Page<PositionEntity> page = positionEntityRepository.findAll((Predicate) filter.toPredicate(), pageable);
        return ConvertUtil.convertToPageInfo(page.map(PositionEntity::toDomain));
    }

    @Override
    public void deletePosition(Long id) {
        positionEntityRepository.delete(positionEntityService.getPosition(id));
    }
}
