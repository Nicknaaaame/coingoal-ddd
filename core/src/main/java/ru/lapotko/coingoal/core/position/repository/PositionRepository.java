package ru.lapotko.coingoal.core.position.repository;

import ru.lapotko.coingoal.core.filtration.PositionFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.request.PositionRequest;

import java.util.Optional;

public interface PositionRepository {

    PositionAggregate createPosition(PositionRequest request);

    void savePosition(PositionAggregate positionAggregate);

    Optional<PositionAggregate> findPositionById(Long id);

    PageInfo<PositionAggregate> findAll(PositionFilterInfo filterInfo, PageableInfo pageableInfo);
}
