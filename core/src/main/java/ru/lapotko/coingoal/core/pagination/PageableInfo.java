package ru.lapotko.coingoal.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageableInfo {
    private int page;
    private int size;
    private SortInfo sort;

    public static PageableInfo of(int page, int size, SortInfo sort) {
        return new PageableInfo(page, size, sort);
    }
}
