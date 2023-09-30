package ru.lapotko.coingoal.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapotko.coingoal.core.exception.CoinNotFoundException;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.CoinJpaRepository;

@Service
@RequiredArgsConstructor
public class CoinJpaService {

    private final CoinJpaRepository coinJpaRepository;

    public CoinEntity getCoin(Long coinId) {
        return coinJpaRepository.findById(coinId).orElseThrow(() -> {
            throw new CoinNotFoundException(coinId);
        });
    }
}
