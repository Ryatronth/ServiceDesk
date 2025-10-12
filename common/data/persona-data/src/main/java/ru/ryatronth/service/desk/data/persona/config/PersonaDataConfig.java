package ru.ryatronth.service.desk.data.persona.config;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("ru.ryatronth.service.desk.data.persona")
@EnableJpaRepositories("ru.ryatronth.service.desk.data.persona")
public class PersonaDataConfig {

    @Bean
    public SpringLiquibase personaLiquibaseDataConfig(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:db/changelog/db.changelog-persona-master.xml");
        return springLiquibase;
    }

}
