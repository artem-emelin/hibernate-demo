package com.dataart.hibernate.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.dataart.hibernate.demo.database.repository"
)
public class DatabaseConfig {

}
