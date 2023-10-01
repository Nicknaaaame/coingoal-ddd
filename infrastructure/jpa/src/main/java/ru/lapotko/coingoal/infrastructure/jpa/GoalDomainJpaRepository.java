package ru.lapotko.coingoal.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.lapotko.coingoal.core.position.Goal;
import ru.lapotko.coingoal.core.position.repository.GoalDomainRepository;
import ru.lapotko.coingoal.core.position.request.GoalRequest;
import ru.lapotko.coingoal.infrastructure.jpa.entity.GoalEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.GoalEntityRepository;
import ru.lapotko.coingoal.infrastructure.jpa.service.PositionEntityService;

@Repository
@RequiredArgsConstructor
public class GoalDomainJpaRepository implements GoalDomainRepository {

    private final PositionEntityService positionEntityService;
    private final GoalEntityRepository goalEntityRepository;

    @Override
    public Goal createGoal(Long positionId, GoalRequest goalRequest) {
        GoalEntity goal = new GoalEntity();
        goal.setPosition(positionEntityService.getPosition(positionId));
        goal.setSellAmount(goalRequest.sellAmount());
        goal.setSellPrice(goalRequest.sellPrice());
        Integer weight = goalEntityRepository.findTopByPositionIdOrderByWeightDesc(positionId)
                .map(goalEntity -> goalEntity.getWeight() + 1)
                .orElse(0);
        goal.setWeight(weight);
        goalEntityRepository.save(goal);
        return goal.toDomain();
    }

    @Override
    public void deleteGoal(Long goalId) {

    }
}
