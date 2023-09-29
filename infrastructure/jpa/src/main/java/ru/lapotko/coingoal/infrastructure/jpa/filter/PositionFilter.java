package ru.lapotko.coingoal.infrastructure.jpa.filter;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapotko.coingoal.infrastructure.jpa.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionFilter {
    private CoinFilter coinFilter;

    public Predicate getFilter() {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(coinFilter.getFilter());
        return PredicateUtil.toPredicate(predicates);
    }
}
