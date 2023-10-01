package ru.lapotko.coingoal.application.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lapotko.coingoal.application.rest.dto.response.PositionResponse;
import ru.lapotko.coingoal.application.rest.mapper.RestMapper;
import ru.lapotko.coingoal.core.pagination.PageInfo;
import ru.lapotko.coingoal.core.position.PositionAggregate;
import ru.lapotko.coingoal.core.position.request.GoalRequest;
import ru.lapotko.coingoal.core.position.request.PositionCreate;
import ru.lapotko.coingoal.core.position.request.PositionUpdate;
import ru.lapotko.coingoal.infrastructure.jpa.filter.CoinFilter;
import ru.lapotko.coingoal.infrastructure.jpa.filter.PositionFilter;
import ru.lapotko.coingoal.infrastructure.jpa.service.PositionApplicationService;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;

import static ru.lapotko.coingoal.application.rest.mapper.RestMapper.toPositionResponse;

@RestController
@RequestMapping("/api/v1/position")
@RequiredArgsConstructor
public class PositionController {
    private final PositionApplicationService positionService;

    @GetMapping
    public ResponseEntity<Page<PositionResponse>> getPositionPage(
            @RequestParam(name = "coin_name", required = false)
            String coinName,
            @RequestParam(name = "coin_symbol", required = false)
            String coinSymbol,
            Pageable pageable) {
        PositionFilter filter = PositionFilter.builder()
                .coinFilter(CoinFilter.builder()
                        .name(coinName)
                        .symbol(coinSymbol)
                        .build())
                .build();
        PageInfo<PositionAggregate> positionPageInfo = positionService.getPositionPage(
                ConvertUtil.convertToPositionFilter(filter),
                ConvertUtil.convertToPageableInfo(pageable));
        Page<PositionAggregate> positionPage = ConvertUtil.convertToPage(positionPageInfo, pageable);
        return ResponseEntity.ok(positionPage.map(RestMapper::toPositionResponse));
    }

    @GetMapping("/{positionId}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long positionId) {
        return ResponseEntity.ok(toPositionResponse(positionService.getPosition(positionId)));
    }

    @PostMapping
    public ResponseEntity<Long> createPosition(
            @RequestBody
            PositionCreate request) {
        PositionAggregate position = positionService.createPosition(request);
        return ResponseEntity.ok(position.getId());
    }

    @DeleteMapping("/{positionId}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long positionId) {
        positionService.deletePosition(positionId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<PositionResponse> patchPosition(
            @RequestBody
            PositionUpdate request) {
        return ResponseEntity.ok(toPositionResponse(positionService.updatePosition(request)));
    }

    @PostMapping("/{positionId}/goal")
    public ResponseEntity<Long> createGoal(
            @PathVariable Long positionId,
            @RequestBody GoalRequest request) {
        return ResponseEntity.ok(positionService.addGoal(positionId, request));
    }

    @DeleteMapping("/{positionId}/goal/{goalId}")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Long positionId,
            @PathVariable Long goalId) {
        positionService.deleteGoal(positionId, goalId);
        return ResponseEntity.ok().build();
    }
}
