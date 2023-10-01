package ru.lapotko.coingoal.infrastructure.jpa.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.lapotko.coingoal.core.position.repository.GoalDomainRepository;
import ru.lapotko.coingoal.core.position.repository.PositionDomainRepository;
import ru.lapotko.coingoal.core.position.service.PositionDomainService;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.lapotko.coingoal.infrastructure.jpa.repository")
public class JpaConfig {
    @Autowired
    Environment env;

    @Bean
    public PositionDomainService domainPositionService(
            PositionDomainRepository positionDomainRepository,
            GoalDomainRepository goalDomainRepository) {
        return new PositionDomainService(positionDomainRepository, goalDomainRepository);
    }

    @Bean
    public DataSource dataSource() {
        boolean isDocker = Arrays.asList(env.getActiveProfiles()).contains("docker");
        String url = isDocker ? "jdbc:postgresql://coingoal-postgres:5432/coingoal"
                : "jdbc:postgresql://localhost:5432/coingoal";
        return DataSourceBuilder
                .create()
                .driverClassName("org.postgresql.Driver")
                .username("postgres")
                .password("postgres")
                .url(url)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ru.lapotko.coingoal.infrastructure.jpa.entity");
        entityManagerFactory.setPersistenceUnitName("name");
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        entityManagerFactory.setJpaProperties(jpaProperties);

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}


