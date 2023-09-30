package ru.lapotko.coingoal.infrastructure.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapotko.coingoal.core.exception.PositionNotFoundException;
import ru.lapotko.coingoal.infrastructure.jpa.entity.PositionEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.PositionJpaRepository;

@Service
@RequiredArgsConstructor
public class PositionJpaService {

    private final PositionJpaRepository positionJpaRepository;

    public void savePosition(PositionEntity positionEntity) {
        positionJpaRepository.save(positionEntity);
    }

    public PositionEntity getPosition(Long id) {
        return positionJpaRepository.findById(id).orElseThrow(() -> {
            throw new PositionNotFoundException(id);
        });
    }
}
