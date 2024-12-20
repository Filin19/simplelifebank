package com.bank.simplelifebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication()
@ComponentScan(basePackages = {"com.bank.simplelifebank"})
@EntityScan(basePackages = {"com.bank.simplelifebank.entity"})
@EnableJpaRepositories
public class SimplelifebankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplelifebankApplication.class, args);
    }

}


