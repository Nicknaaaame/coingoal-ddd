package ru.lapotko.coingoal.core.filtration;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class StringFilterInfo {
    private final String cont;
    private final String eq;
}
