package ru.lapotko.coingoal.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PageInfo<T> {
    private List<T> content;
    private long totalElements;

    public static <T> PageInfo<T> of(List<T> content, long totalElements) {
        return new PageInfo<>(content, totalElements);
    }
}
