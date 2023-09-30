package ru.lapotko.coingoal.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;

public interface CoinEntityRepository extends JpaRepository<CoinEntity, Long>, QuerydslPredicateExecutor<CoinEntity> {
}
