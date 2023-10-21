package ru.lapotko.coingoal.core.filtration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class StringDomainFilter implements Filterable {
    protected final StringFilterInfo filterInfo;
}
