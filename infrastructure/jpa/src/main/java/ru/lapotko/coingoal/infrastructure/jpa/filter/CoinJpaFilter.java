package ru.lapotko.coingoal.infrastructure.jpa.filter;

import com.querydsl.core.types.Predicate;
import ru.lapotko.coingoal.core.filtration.CoinDomainFilter;
import ru.lapotko.coingoal.core.filtration.StringFilterInfo;
import ru.lapotko.coingoal.infrastructure.jpa.entity.QCoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;

public class CoinJpaFilter extends CoinDomainFilter {

    public CoinJpaFilter(StringFilterInfo name, StringFilterInfo symbol) {
        super(new StringJpaFilter(name, QCoinEntity.coinEntity.name),
                new StringJpaFilter(symbol, QCoinEntity.coinEntity.symbol));
    }

    public static CoinJpaFilter empty() {
        return new CoinJpaFilter(StringFilterInfo.builder().build(), StringFilterInfo.builder().build());
    }

    @Override
    public Predicate toPredicate() {
        List<Predicate> predicates = new ArrayList<>();
        if (this.name != null) {
            predicates.add((Predicate) this.name.toPredicate());
        }
        if (this.symbol != null) {
            predicates.add((Predicate) this.symbol.toPredicate());
        }
        return PredicateUtil.toPredicate(predicates);
    }
}
