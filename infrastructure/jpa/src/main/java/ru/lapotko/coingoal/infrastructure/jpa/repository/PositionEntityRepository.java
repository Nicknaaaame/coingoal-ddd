package ru.lapotko.coingoal.infrastructure.jpa.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;
import ru.lapotko.coingoal.infrastructure.jpa.entity.PositionEntity;

import java.util.Optional;

public interface PositionEntityRepository extends JpaRepository<PositionEntity, Long>, QuerydslPredicateExecutor<PositionEntity> {
    @NonNull
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"coin", "goals"})
    Page<PositionEntity> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

    @Override
    @NonNull
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"coin", "goals"})
    Optional<PositionEntity> findById(@NonNull Long id);
}
