package ru.lapotko.coingoal.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.lapotko.coingoal.core.filtration.CoinFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.repository.CoinRepository;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.filter.CoinFilter;
import ru.lapotko.coingoal.infrastructure.jpa.repository.CoinJpaRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CoinDomainRepository implements CoinRepository {

    private final CoinJpaRepository coinJpaRepository;
    @Override
    public Optional<Coin> findCoinById(Long coinId) {
        return coinJpaRepository.findById(coinId).map(CoinEntity::toDomain);
    }

    @Override
    public PageInfo<Coin> findAll(CoinFilterInfo filterInfo, PageableInfo pageableInfo) {
        Pageable pageable = ConvertUtil.convertToPageable(pageableInfo);
        CoinFilter filter = ConvertUtil.convertToCoinFilter(filterInfo);
        Page<CoinEntity> page = coinJpaRepository.findAll(filter.getFilter(), pageable);
        return ConvertUtil.convertToPageInfo(page.map(CoinEntity::toDomain));
    }
}
