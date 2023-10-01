package ru.lapotko.coingoal.core.position.repository;

import ru.lapotko.coingoal.core.position.Goal;
import ru.lapotko.coingoal.core.position.request.GoalRequest;

public interface GoalDomainRepository {
    Goal createGoal(Long positionId, GoalRequest goalRequest);

    void deleteGoal(Long goalId);
}
