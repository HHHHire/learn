package com.example.springbootdemowebsocket.util;

import com.example.springbootdemowebsocket.config.RabbitMQConfig;
import com.example.springbootdemowebsocket.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: ddh
 * @data: 2019/11/29 9:34
 * @description
 **/
@Component
@Slf4j
public class Sender {
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public Sender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendTopic() {
        User zhangsan = User.builder().userId("11111")
                .userName("zhangsan").build();
        User lisi = User.builder().userId("222")
                .userName("lisi").build();
        log.info(String.valueOf(LocalDateTime.now()));
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_QUEUE, "zhangsan");
        log.info("[发送成功]");
    }
}
