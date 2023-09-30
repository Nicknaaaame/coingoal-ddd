package ru.lapotko.coingoal.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapotko.coingoal.infrastructure.jpa.entity.GoalEntity;

import java.util.Optional;

public interface GoalEntityRepository extends JpaRepository<GoalEntity, Long> {
    Optional<GoalEntity> findTopByPositionIdOrderByWeightDesc(Long positionId);
}
