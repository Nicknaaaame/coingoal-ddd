package ru.lapotko.coingoal.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.repository.PositionRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PositionDomainRepository implements PositionRepository {
    @Override
    public void savePosition(PositionAggregate positionAggregate) {

    }

    @Override
    public Optional<PositionAggregate> findPositionById(Long id) {
        return Optional.empty();
    }
}
