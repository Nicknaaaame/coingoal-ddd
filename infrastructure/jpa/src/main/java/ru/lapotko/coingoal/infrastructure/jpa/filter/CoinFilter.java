package ru.lapotko.coingoal.infrastructure.jpa.filter;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapotko.coingoal.infrastructure.jpa.entity.QCoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinFilter {
    private String name;
    private String symbol;

    public Predicate getFilter() {
        List<Predicate> predicates = new ArrayList<>();
        if (!Objects.isNull(this.getName())) {
            predicates.add(QCoinEntity.coinEntity.name.containsIgnoreCase(this.getName()));
        }
        if (!Objects.isNull(this.getSymbol())) {
            predicates.add(QCoinEntity.coinEntity.symbol.containsIgnoreCase(this.getSymbol()));
        }
        return PredicateUtil.toPredicate(predicates);
    }
}
