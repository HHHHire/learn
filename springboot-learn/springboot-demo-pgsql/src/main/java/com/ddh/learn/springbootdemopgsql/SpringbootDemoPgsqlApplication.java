package com.ddh.learn.springbootdemopgsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootDemoPgsqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoPgsqlApplication.class, args);
        String str = "1234";
        String zero = "000000000000";
//        String format = String.format("%-12s", str, "0");
//        System.out.println(format + ".");
        String s = str + zero.substring(0, 12 - str.length());
        System.out.println(s);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
