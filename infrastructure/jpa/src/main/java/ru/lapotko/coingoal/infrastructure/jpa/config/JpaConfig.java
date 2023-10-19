package ru.lapotko.coingoal.infrastructure.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.lapotko.coingoal.core.position.repository.GoalDomainRepository;
import ru.lapotko.coingoal.core.position.repository.PositionDomainRepository;
import ru.lapotko.coingoal.core.position.service.PositionDomainService;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.lapotko.coingoal.infrastructure.jpa.repository")
@EntityScan("ru.lapotko.coingoal.infrastructure.jpa.entity")
public class JpaConfig {

    @Bean
    public PositionDomainService domainPositionService(
            PositionDomainRepository positionDomainRepository,
            GoalDomainRepository goalDomainRepository) {
        return new PositionDomainService(positionDomainRepository, goalDomainRepository);
    }

}


