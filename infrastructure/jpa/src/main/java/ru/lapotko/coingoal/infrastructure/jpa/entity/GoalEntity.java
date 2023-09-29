package ru.lapotko.coingoal.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "goal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal sellAmount;

    private BigDecimal sellPrice;

    private Integer weight;

    @ManyToOne
    private PositionEntity position;
}
