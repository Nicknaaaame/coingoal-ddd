package ru.lapotko.coingoal.core.position.repository;

import ru.lapotko.coingoal.core.position.Coin;

import java.util.Optional;

public interface CoinRepository {
    Optional<Coin> findCoinById(Long coinId);
}
