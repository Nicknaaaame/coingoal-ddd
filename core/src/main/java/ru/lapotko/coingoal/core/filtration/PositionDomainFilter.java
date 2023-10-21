package ru.lapotko.coingoal.core.filtration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PositionDomainFilter implements Filterable {
    protected final CoinDomainFilter coinFilter;
}
