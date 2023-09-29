package ru.lapotko.coingoal.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class SortInfo {
    private List<OrderInfo> orders;

    public static SortInfo of(List<OrderInfo> orders) {
        return new SortInfo(Collections.unmodifiableList(orders));
    }
}
