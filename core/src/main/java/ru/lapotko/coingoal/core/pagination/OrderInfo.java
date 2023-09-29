package ru.lapotko.coingoal.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderInfo {
    private String property;
    private SortDirectionInfo direction;

    public static OrderInfo of(String property, SortDirectionInfo direction) {
        return new OrderInfo(property, direction);
    }
}
