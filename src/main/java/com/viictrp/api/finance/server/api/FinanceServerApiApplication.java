package com.viictrp.api.finance.server.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan("com.viictrp.api.finance.server.api.*")
@EnableJpaRepositories(basePackages = "com.viictrp.api.finance.server.api.persistence")
@EnableMongoRepositories(basePackages = "com.viictrp.api.finance.server.api.oauth.repository")
public class FinanceServerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceServerApiApplication.class, args);
    }
}
