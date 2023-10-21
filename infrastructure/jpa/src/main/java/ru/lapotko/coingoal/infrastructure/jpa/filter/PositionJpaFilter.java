package ru.lapotko.coingoal.infrastructure.jpa.filter;

import com.querydsl.core.types.Predicate;
import ru.lapotko.coingoal.core.filtration.CoinDomainFilter;
import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.infrastructure.jpa.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;

public class PositionJpaFilter extends PositionDomainFilter {
    public PositionJpaFilter(CoinDomainFilter coinFilter) {
        super(coinFilter);
    }

    @Override
    public Object toPredicate() {
        List<Predicate> predicates = new ArrayList<>();
        if (this.coinFilter != null) {
            predicates.add((Predicate) this.coinFilter.toPredicate());
        }
        return PredicateUtil.toPredicate(predicates);
    }
}
