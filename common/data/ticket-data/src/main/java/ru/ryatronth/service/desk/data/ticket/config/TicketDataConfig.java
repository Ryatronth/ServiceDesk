package ru.ryatronth.service.desk.data.ticket.config;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("ru.ryatronth.service.desk.data.ticket")
@EnableJpaRepositories("ru.ryatronth.service.desk.data.ticket")
public class TicketDataConfig {

    @Bean
    public SpringLiquibase ticketLiquibaseDataConfig(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:db/changelog/db.changelog-ticket-master.xml");
        return springLiquibase;
    }

}
