package ru.lapotko.coingoal.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.lapotko.coingoal.core.filtration.PositionFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.Goal;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.repository.PositionRepository;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.entity.PositionEntity;
import ru.lapotko.coingoal.infrastructure.jpa.filter.PositionFilter;
import ru.lapotko.coingoal.infrastructure.jpa.repository.GoalJpaRepository;
import ru.lapotko.coingoal.infrastructure.jpa.repository.PositionJpaRepository;
import ru.lapotko.coingoal.infrastructure.jpa.service.PositionJpaService;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PositionDomainRepository implements PositionRepository {

    private final CoinJpaService coinJpaService;
    private final PositionJpaService positionJpaService;
    private final PositionJpaRepository positionJpaRepository;
    private final GoalJpaRepository goalJpaRepository;
    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public PositionAggregate createPosition(PositionCreate positionRequest) {
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setUserId("ADMIN_USER");
        positionEntity.setAvgBuyPrice(positionRequest.avgBuyPrice());
        positionEntity.setHoldings(positionRequest.holdings());
        CoinEntity coin = coinJpaService.getCoin(positionRequest.coinId());
        positionEntity.setCoin(coin);

        positionJpaService.savePosition(positionEntity);

        return positionEntity.toDomain();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void savePosition(PositionAggregate positionAggregate) {
        PositionEntity positionEntity = positionJpaService.getPosition(positionAggregate.getId());
        positionEntity.setUserId("ADMIN_USER");
        positionEntity.setAvgBuyPrice(positionAggregate.getAvgBuyPrice().fiat());
        positionEntity.setHoldings(positionAggregate.currentHoldings().amount());
        positionEntity.setCoin(coinJpaService.getCoin(positionAggregate.getCoin().id()));

        List<Long> goalIds = positionAggregate.getGoalDefinitions().stream().map(Goal::getId).toList();

        positionEntity.setGoals(goalJpaRepository.findAllById(goalIds));
        positionJpaService.savePosition(positionEntity);
    }

    @Override
    public Optional<PositionAggregate> findPositionById(Long id) {
        return positionJpaRepository.findById(id).map(PositionEntity::toDomain);
    }

    @Override
    public PageInfo<PositionAggregate> findAll(PositionFilterInfo filterInfo, PageableInfo pageableInfo) {
        PositionFilter filter = ConvertUtil.convertToPositionFilter(filterInfo);
        Pageable pageable = ConvertUtil.convertToPageable(pageableInfo);
        Page<PositionEntity> page = positionJpaRepository.findAll(filter.getFilter(), pageable);
        return ConvertUtil.convertToPageInfo(page.map(PositionEntity::toDomain));
    }

    @Override
    public void deletePosition(Long id) {
        positionJpaRepository.delete(positionJpaService.getPosition(id));
    }
}
