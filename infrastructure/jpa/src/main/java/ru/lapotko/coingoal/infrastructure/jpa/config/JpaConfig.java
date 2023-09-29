package ru.lapotko.coingoal.infrastructure.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lapotko.coingoal.core.position.repository.CoinRepository;
import ru.lapotko.coingoal.core.position.repository.PositionRepository;
import ru.lapotko.coingoal.core.position.service.DomainPositionService;

@Configuration
public class JpaConfig {
    @Bean
    public DomainPositionService domainPositionService(
            PositionRepository positionRepository,
            CoinRepository coinRepository) {
        return new DomainPositionService(positionRepository, coinRepository);
    }
}
