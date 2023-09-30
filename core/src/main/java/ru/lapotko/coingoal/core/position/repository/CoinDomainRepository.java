package ru.lapotko.coingoal.core.position.repository;

import ru.lapotko.coingoal.core.filtration.CoinFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.Coin;

import java.util.Optional;

public interface CoinDomainRepository {
    Optional<Coin> findCoinById(Long coinId);

    PageInfo<Coin> findAll(CoinFilterInfo filter, PageableInfo pageable);
}
