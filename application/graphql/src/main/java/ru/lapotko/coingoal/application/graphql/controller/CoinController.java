package ru.lapotko.coingoal.application.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.lapotko.coingoal.application.graphql.mapper.GraphQlMapper;
import ru.lapotko.coingoal.application.graphql.types.CoinFilterInput;
import ru.lapotko.coingoal.application.graphql.types.CoinSortInput;
import ru.lapotko.coingoal.application.graphql.types.CoinType;
import ru.lapotko.coingoal.core.filtration.CoinDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.pagination.SortInfo;
import ru.lapotko.coingoal.core.position.repository.CoinDomainRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CoinController {
    private final CoinDomainRepository coinDomainRepository;

    @QueryMapping
    public Iterable<CoinType> coins(
            @Argument CoinFilterInput filter,
            @Argument List<CoinSortInput> sort,
            @Argument Integer page,
            @Argument Integer size) {
        CoinDomainFilter domainFilter = GraphQlMapper.toCoinDomainFilter(filter);
        PageableInfo pageable = PageableInfo.of(
                page,
                size,
                SortInfo.of(sort.stream()
                        .map(GraphQlMapper::toOrderInfo)
                        .collect(Collectors.toList())));
        return coinDomainRepository.findAll(domainFilter, pageable).getContent().stream()
                .map(GraphQlMapper::toCoinType)
                .collect(Collectors.toList());
    }
}
