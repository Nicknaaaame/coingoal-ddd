package ru.lapotko.coingoal.application.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.lapotko.coingoal.infrastructure.jpa.config.JpaConfig;

@SpringBootApplication
@Import(JpaConfig.class)
@ComponentScan(basePackages = {"ru.lapotko.coingoal.application.graphql", "ru.lapotko.coingoal.infrastructure.jpa"})
public class GraphQlCoinGoalApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphQlCoinGoalApplication.class, args);
    }
}
