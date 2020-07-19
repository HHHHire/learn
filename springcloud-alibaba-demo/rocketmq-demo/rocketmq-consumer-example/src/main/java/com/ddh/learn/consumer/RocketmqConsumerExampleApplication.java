package com.ddh.learn.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding({Source.class, RocketmqConsumerExampleApplication.MySink.class})
public class RocketmqConsumerExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqConsumerExampleApplication.class, args);
    }

    public interface MySink {
        @Input("input1")
        SubscribableChannel input1();
    }

}
