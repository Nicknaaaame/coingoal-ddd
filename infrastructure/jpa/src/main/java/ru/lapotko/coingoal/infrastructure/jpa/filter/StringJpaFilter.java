package ru.lapotko.coingoal.infrastructure.jpa.filter;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.util.StringUtils;
import ru.lapotko.coingoal.core.filtration.StringDomainFilter;
import ru.lapotko.coingoal.core.filtration.StringFilterInfo;
import ru.lapotko.coingoal.infrastructure.jpa.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;

public class StringJpaFilter extends StringDomainFilter {
    private final StringPath field;

    public StringJpaFilter(StringFilterInfo filterInfo, StringPath field) {
        super(filterInfo);
        this.field = field;
    }

    @Override
    public Predicate toPredicate() {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(this.filterInfo.getEq())) {
            predicates.add(field.equalsIgnoreCase(this.filterInfo.getEq()));
        }
        if (StringUtils.hasText(this.filterInfo.getCont())) {
            predicates.add(field.containsIgnoreCase(this.filterInfo.getCont()));
        }
        return PredicateUtil.toPredicate(predicates);
    }


}
