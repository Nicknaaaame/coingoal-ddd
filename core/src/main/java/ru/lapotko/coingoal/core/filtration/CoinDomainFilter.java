package ru.lapotko.coingoal.core.filtration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CoinDomainFilter implements Filterable {
    protected final StringDomainFilter name;
    protected final StringDomainFilter symbol;
}
