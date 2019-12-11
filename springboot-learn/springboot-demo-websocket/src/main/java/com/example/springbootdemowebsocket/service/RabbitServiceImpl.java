package com.example.springbootdemowebsocket.service;

import com.example.springbootdemowebsocket.server.SocketServer;
import com.example.springbootdemowebsocket.util.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author: ddh
 * @data: 2019/12/2 16:38
 * @description
 **/
@Service
@Slf4j
public class RabbitServiceImpl {
    private final Consumer consumer;
    private final SocketServer socketServer;

    @Autowired
    public RabbitServiceImpl(Consumer consumer, SocketServer socketServer) {
        this.consumer = consumer;
        this.socketServer = socketServer;
    }

    @Scheduled(cron = "0/30 * * * * *")
    public void recieve() {
        System.out.println("have a look");

        String recieve = consumer.recieve();
        log.info(recieve);
        if (recieve != null && !recieve.isEmpty()) {
            socketServer.sendAll(recieve);
        }
    }
}
