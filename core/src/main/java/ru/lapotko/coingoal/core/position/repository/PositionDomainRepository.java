package ru.lapotko.coingoal.core.position.repository;

import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.request.PositionCreate;

import java.util.Optional;

public interface PositionDomainRepository {

    PositionAggregate createPosition(PositionCreate request);

    void savePosition(PositionAggregate positionAggregate);

    Optional<PositionAggregate> findPositionById(Long id);

    PageInfo<PositionAggregate> findAll(PositionDomainFilter filter, PageableInfo pageableInfo);

    void deletePosition(Long id);
}
