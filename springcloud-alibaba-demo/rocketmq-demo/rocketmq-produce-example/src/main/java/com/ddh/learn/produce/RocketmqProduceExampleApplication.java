package com.ddh.learn.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@EnableBinding({RocketmqProduceExampleApplication.MySource.class})
public class RocketmqProduceExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProduceExampleApplication.class, args);
    }

    public interface MySource{
        @Output("output1")
        MessageChannel output1();
    }
}
