package com.viictrp.api.finance.server.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan("com.viictrp.api.finance.server.api.*")
@EnableMongoRepositories(basePackages = {
        "com.viictrp.api.finance.server.api.oauth.repository",
        "com.viictrp.api.finance.server.api.persistence"
})
public class FinanceServerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceServerApiApplication.class, args);
    }
}
