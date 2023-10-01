package ru.lapotko.coingoal.application.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lapotko.coingoal.application.rest.dto.response.CoinResponse;
import ru.lapotko.coingoal.application.rest.mapper.RestMapper;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.position.repository.CoinDomainRepository;
import ru.lapotko.coingoal.infrastructure.jpa.filter.CoinFilter;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;

@RestController
@RequestMapping("/api/v1/coin")
@RequiredArgsConstructor
public class CoinController {
    private final CoinDomainRepository coinDomainRepository;

    @GetMapping
    public ResponseEntity<Page<CoinResponse>> getCoinsPage(
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            String symbol,
            @PageableDefault(direction = Sort.Direction.ASC)
            Pageable pageable) {
        CoinFilter filter = CoinFilter.builder()
                .name(name)
                .symbol(symbol)
                .build();
        PageInfo<Coin> coins = coinDomainRepository.findAll(
                ConvertUtil.convertToCoinFilterInfo(filter),
                ConvertUtil.convertToPageableInfo(pageable));
        return ResponseEntity.ok(ConvertUtil.convertToPage(coins, pageable).map(RestMapper::toCoinResponse));
    }
}
