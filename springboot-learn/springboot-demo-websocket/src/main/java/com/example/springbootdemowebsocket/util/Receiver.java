package com.example.springbootdemowebsocket.util;

import com.example.springbootdemowebsocket.config.RabbitMQConfig;
import com.example.springbootdemowebsocket.entity.User;
import com.example.springbootdemowebsocket.server.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;

/**
 * @author: ddh
 * @data: 2019/11/29 9:34
 * @description
 **/
//@Component
@Slf4j
public class Receiver {
    @Autowired
    private SocketServer socketServer;

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE)
    @Async
    public void receiverTopic1(User user) {
        log.info("topic收到消息" + user.toString() + "时间:" + LocalDateTime.now() + Thread.currentThread().getName());
        socketServer.sendAll(user.getUserName());
    }

}
