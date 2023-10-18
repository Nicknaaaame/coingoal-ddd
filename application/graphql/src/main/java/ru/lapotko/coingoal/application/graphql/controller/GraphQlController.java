package ru.lapotko.coingoal.application.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.lapotko.coingoal.application.graphql.mapper.GraphQlMapper;
import ru.lapotko.coingoal.application.graphql.types.types.CoinGql;
import ru.lapotko.coingoal.core.filtration.CoinFilterInfo;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.pagination.SortInfo;
import ru.lapotko.coingoal.core.position.repository.CoinDomainRepository;
import ru.lapotko.coingoal.infrastructure.jpa.entity.PositionEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.PositionEntityRepository;
import ru.lapotko.coingoal.infrastructure.jpa.service.PositionApplicationService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GraphQlController {
    private final PositionApplicationService positionService;
    private final PositionEntityRepository repository;
    private final CoinDomainRepository coinDomainRepository;

    @QueryMapping
    public Iterable<PositionEntity> positions() {
        return repository.findAll();
    }

    @QueryMapping
    public Iterable<CoinGql> coins(@Argument Integer page, @Argument Integer size) {
        return coinDomainRepository.findAll(
                        CoinFilterInfo.builder().build(),
                        PageableInfo.of(page, size, SortInfo.of(Collections.emptyList())))
                .getContent().stream()
                .map(GraphQlMapper::toCoinGql)
                .collect(Collectors.toList());
    }
}
