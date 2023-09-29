package ru.lapotko.coingoal.infrastructure.jpa.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.List;

public final class PredicateUtil {
    public static Predicate toPredicate(List<Predicate> predicates) {
        BooleanBuilder builder = new BooleanBuilder();
        predicates.forEach(builder::and);
        return builder;
    }
}
