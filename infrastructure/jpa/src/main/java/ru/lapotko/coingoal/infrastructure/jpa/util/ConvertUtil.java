package ru.lapotko.coingoal.infrastructure.jpa.util;

import org.springframework.data.domain.*;
import ru.lapotko.coingoal.core.pagination.*;
import ru.lapotko.coingoal.infrastructure.jpa.dto.CoinDto;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtil {

    public static <T> Page<T> convertToPage(PageInfo<T> pageInfo, Pageable pageable) {
        return new PageImpl<>(pageInfo.getContent(), pageable, pageInfo.getTotalElements());
    }

    public static <T> PageInfo<T> convertToPageInfo(Page<T> page) {
        return PageInfo.of(page.getContent(), page.getTotalElements());
    }

    public static PageableInfo convertToPageableInfo(Pageable pageable) {
        return PageableInfo.of(pageable.getPageNumber(), pageable.getPageSize(), convertToSortInfo(pageable.getSort()));
    }

    public static SortInfo convertToSortInfo(Sort sort) {
        List<OrderInfo> orders = sort.stream()
                .map(order -> OrderInfo.of(order.getProperty(), convertToSortDirectionInfo(order.getDirection())))
                .collect(Collectors.toList());
        return SortInfo.of(orders);
    }

    public static SortDirectionInfo convertToSortDirectionInfo(Sort.Direction direction) {
        if (Sort.Direction.ASC.equals(direction))
            return SortDirectionInfo.ASC;
        return SortDirectionInfo.DESC;
    }

    public static Pageable convertToPageable(PageableInfo pageableInfo) {
        return PageRequest.of(
                pageableInfo.getPage(),
                pageableInfo.getSize(),
                convertToSort(pageableInfo.getSort()));
    }

    public static Sort convertToSort(SortInfo sortInfo) {
        List<Sort.Order> orders = sortInfo.getOrders().stream()
                .map(orderInfo -> new Sort.Order(convertToDirection(orderInfo.getDirection()), orderInfo.getProperty()))
                .collect(Collectors.toList());
        return Sort.by(orders);
    }

    public static Sort.Direction convertToDirection(SortDirectionInfo directionInfo) {
        if (SortDirectionInfo.ASC.equals(directionInfo))
            return Sort.Direction.ASC;
        return Sort.Direction.DESC;
    }

    public static CoinEntity convertToCoinEntity(CoinDto coinDto) {
        return new CoinEntity(
                null,
                coinDto.getPrice(),
                coinDto.getName(),
                coinDto.getSymbol(),
                coinDto.getChange24h()
        );
    }

}
