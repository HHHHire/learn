package com.ddh.learn.springbootdemopgsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootDemoPgsqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoPgsqlApplication.class, args);
    }

}
