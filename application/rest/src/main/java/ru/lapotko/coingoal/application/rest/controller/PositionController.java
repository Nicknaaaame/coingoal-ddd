package ru.lapotko.coingoal.application.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lapotko.coingoal.application.rest.dto.response.PositionResponse;
import ru.lapotko.coingoal.core.position.service.DomainPositionService;
import ru.lapotko.coingoal.infrastructure.jpa.PositionDomainRepository;
import ru.lapotko.coingoal.infrastructure.jpa.filter.CoinFilter;
import ru.lapotko.coingoal.infrastructure.jpa.filter.PositionFilter;

@RestController
@RequestMapping("/api/v1/position")
@RequiredArgsConstructor
public class PositionController {
    /*private final DomainPositionService positionService;
    private final CoinGoalMapper mapper = new CoinGoalMapper();

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
        return ResponseEntity.ok(positionService.getPositionResponsePage(filter, pageable));
    }*/

    /*@GetMapping("/{positionId}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long positionId) {
        return ResponseEntity.ok(positionCalculator.calculate(positionService.getPositionById(positionId)));
    }

    @PostMapping
    public ResponseEntity<Long> createPosition(
            @Valid
            @RequestBody
            PositionRequest request) {
        Position position = positionService.createPosition(request);
        return ResponseEntity.ok(position.getId());
    }

    @DeleteMapping("/{positionId}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long positionId) {
        positionService.deletePosition(positionId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<PositionResponse> patchPosition(
            @Valid
            @RequestBody
            PositionPatch request) {
        return ResponseEntity.ok(positionCalculator.calculate(positionService.patchPosition(request)));
    }

    @PostMapping("/{positionId}/goal")
    public ResponseEntity<Long> createGoal(
            @Valid @RequestBody GoalRequest request,
            @PathVariable Long positionId) {
//        return ResponseEntity.ok(goalService.createGoal(request).getId());
        return null;
    }

    @DeleteMapping("/{positionId}/goal/{goalId}")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Long positionId,
            @PathVariable Long goalId) {
//        goalService.deleteGoal(goalId);
        return ResponseEntity.ok().build();
    }*/
}
