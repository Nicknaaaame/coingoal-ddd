package ru.lapotko.coingoal.infrastructure.jpa;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.lapotko.coingoal.core.filtration.CoinDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.repository.CoinDomainRepository;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.CoinEntityRepository;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CoinDomainJpaRepository implements CoinDomainRepository {

    private final CoinEntityRepository coinEntityRepository;

    @Override
    public Optional<Coin> findCoinById(Long coinId) {
        return coinEntityRepository.findById(coinId).map(CoinEntity::toDomain);
    }

    @Override
    public PageInfo<Coin> findAll(CoinDomainFilter filter, PageableInfo pageableInfo) {
        Pageable pageable = ConvertUtil.convertToPageable(pageableInfo);
        Page<CoinEntity> page = coinEntityRepository.findAll((Predicate) filter.toPredicate(), pageable);
        return ConvertUtil.convertToPageInfo(page.map(CoinEntity::toDomain));
    }
}
