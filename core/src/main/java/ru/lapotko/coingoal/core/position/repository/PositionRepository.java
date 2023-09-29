package ru.lapotko.coingoal.core.position.repository;

import ru.lapotko.coingoal.core.position.PositionAggregate;

import java.util.Optional;

public interface PositionRepository {
    void savePosition(PositionAggregate positionAggregate);

    Optional<PositionAggregate> findPositionById(Long id);
}
