package com.example.springbootdemowebsocket.controller;

import com.example.springbootdemowebsocket.config.RabbitMQConfig;
import com.example.springbootdemowebsocket.entity.User;
import com.example.springbootdemowebsocket.service.RabbitServiceImpl;
import com.example.springbootdemowebsocket.util.Receiver;
import com.example.springbootdemowebsocket.util.Sender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddh
 * @data: 2019/11/29 9:48
 * @description
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {
    private final Sender sender;
    private final RabbitServiceImpl rabbitService;

    @Autowired
    public RabbitMqController(Sender sender, RabbitServiceImpl rabbitService) {
        this.sender = sender;
        this.rabbitService = rabbitService;
    }

    @GetMapping
    public String sendTopic() {
        sender.sendTopic();
        return "ok";
    }


}
