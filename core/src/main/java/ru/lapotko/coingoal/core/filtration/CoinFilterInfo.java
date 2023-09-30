package ru.lapotko.coingoal.core.filtration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CoinFilterInfo {
    private final String name;
    private final String symbol;
}
