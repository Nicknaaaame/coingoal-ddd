package ru.lapotko.coingoal.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.lapotko.coingoal.core.position.PositionAggregate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "position")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "holdings")
    private BigDecimal holdings;

    @Column(name = "avg_buy_price")
    private BigDecimal avgBuyPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coin_id")
    private CoinEntity coin;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
    private List<GoalEntity> goals;

    public PositionAggregate toDomain() {
        return new PositionAggregate.PositionBuilder()
                .id(this.getId())
                .avgBuyPrice(this.getAvgBuyPrice())
                .holdings(this.getHoldings())
                .userId(this.getUserId())
                .coin(coin.toDomain())
                .withGoals(goals.stream().map(GoalEntity::toDomain).collect(Collectors.toList()))
                .build();
    }
}
