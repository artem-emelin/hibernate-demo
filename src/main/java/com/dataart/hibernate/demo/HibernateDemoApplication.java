package com.dataart.hibernate.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.dataart.hibernate.demo")
@EnableJpaRepositories(
        basePackages = "com.dataart.hibernate.demo.database.repository"
)
public class HibernateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

}
