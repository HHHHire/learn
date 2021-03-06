package cn.edu.jxust.springbootdemomongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class SpringbootDemoMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoMongodbApplication.class, args);
    }

}
