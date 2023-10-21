package ru.lapotko.coingoal.application.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.lapotko.coingoal.application.graphql.mapper.GraphQlMapper;
import ru.lapotko.coingoal.application.graphql.types.PositionFilterInput;
import ru.lapotko.coingoal.application.graphql.types.PositionSortInput;
import ru.lapotko.coingoal.application.graphql.types.PositionType;
import ru.lapotko.coingoal.core.filtration.PositionDomainFilter;
import ru.lapotko.coingoal.core.pagination.PageableInfo;
import ru.lapotko.coingoal.core.pagination.SortInfo;
import ru.lapotko.coingoal.infrastructure.jpa.service.PositionApplicationService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PositionController {
    private final PositionApplicationService positionService;

    @QueryMapping
    public Iterable<PositionType> positions(
            @Argument PositionFilterInput filter,
            @Argument List<PositionSortInput> sort,
            @Argument Integer page,
            @Argument Integer size
    ) {
        PositionDomainFilter domainFilter = GraphQlMapper.toPositionDomainFilter(filter);
        PageableInfo pageable = PageableInfo.of(
                page,
                size,
                SortInfo.of(sort.stream()
                        .map(GraphQlMapper::toOrderInfo)
                        .collect(Collectors.toList())));
        return positionService.getPositionPage(domainFilter, pageable).getContent().stream()
                .map(GraphQlMapper::toPositionType)
                .collect(Collectors.toList());
    }
}
