package ru.ryatronth.service.desk.data.branch.config;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("ru.ryatronth.service.desk.data.branch")
@EnableJpaRepositories("ru.ryatronth.service.desk.data.branch")
public class BranchDataConfig {

    @Bean
    public SpringLiquibase branchLiquibaseDataConfig(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:db/changelog/db.changelog-branch-master.xml");
        return springLiquibase;
    }

}
