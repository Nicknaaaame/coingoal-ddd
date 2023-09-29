package ru.lapotko.coingoal.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapotko.coingoal.core.position.Coin;
import ru.lapotko.coingoal.core.valueobjects.Change;
import ru.lapotko.coingoal.core.valueobjects.CoinName;
import ru.lapotko.coingoal.core.valueobjects.CoinSymbol;
import ru.lapotko.coingoal.core.valueobjects.FiatAmount;

import java.math.BigDecimal;

@Entity(name = "coin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(scale = 10, precision = 20)
    private BigDecimal price;

    private String name;

    private String symbol;

    private BigDecimal change24h;

    public Coin toDomain() {
        return new Coin(
                id,
                new FiatAmount(price),
                new CoinName(name),
                new CoinSymbol(symbol),
                new Change(change24h));
    }
}
