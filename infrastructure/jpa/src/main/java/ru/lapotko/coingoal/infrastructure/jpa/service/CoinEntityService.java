package ru.lapotko.coingoal.infrastructure.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapotko.coingoal.core.exception.CoinNotFoundException;
import ru.lapotko.coingoal.infrastructure.jpa.entity.CoinEntity;
import ru.lapotko.coingoal.infrastructure.jpa.repository.CoinEntityRepository;

@Service
@RequiredArgsConstructor
public class CoinEntityService {

    private final CoinEntityRepository coinEntityRepository;

    public CoinEntity getCoin(Long coinId) {
        return coinEntityRepository.findById(coinId).orElseThrow(() -> {
            throw new CoinNotFoundException(coinId);
        });
    }
}
