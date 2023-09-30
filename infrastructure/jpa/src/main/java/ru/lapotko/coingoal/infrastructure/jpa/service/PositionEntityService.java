package ru.lapotko.coingoal.infrastructure.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapotko.coingoal.core.exception.PositionNotFoundException;
import ru.lapotko.coingoal.infrastructure.jpa.entity.PositionEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.PositionEntityRepository;

@Service
@RequiredArgsConstructor
public class PositionEntityService {

    private final PositionEntityRepository positionEntityRepository;

    public void savePosition(PositionEntity positionEntity) {
        positionEntityRepository.save(positionEntity);
    }

    public PositionEntity getPosition(Long id) {
        return positionEntityRepository.findById(id).orElseThrow(() -> {
            throw new PositionNotFoundException(id);
        });
    }
}
