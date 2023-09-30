package ru.lapotko.coingoal.core.filtration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PositionFilterInfo {
    private final CoinFilterInfo coinFilter;
}
