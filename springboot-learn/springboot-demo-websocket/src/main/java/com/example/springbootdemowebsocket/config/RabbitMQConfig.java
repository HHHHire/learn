package com.example.springbootdemowebsocket.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: ddh
 * @data: 2019/11/29 9:13
 * @description
 **/
@Configuration
public class RabbitMQConfig {
    public static final String TOPIC_QUEUE = "queue";
    public static final String TOPIC_EXCHANGE = "exchange";
    public static final String DEAD_QUEUE = "dead.queue";
    public static final String ROUTING_KEY = "ddh.message";

    /**
     * topic 队列
     *
     * @return Queue
     */
    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE, true, false, false);
    }

    /**
     * 交换机
     *
     * @return TopicExchange
     */
    @Bean
    public DirectExchange topicExchange() {
        return new DirectExchange(TOPIC_EXCHANGE, false, false);
    }

    /**
     * 绑定队列和交换机
     *
     * @return Binding
     */
    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("ddh.message");
    }

    /**
     * 配置死信队列
     *
     * @return Queue
     */
    /*@Bean
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-message-ttl", 1000 * 10);
        args.put("x-dead-letter-exchange", TOPIC_EXCHANGE);
        args.put("x-dead-letter-routing-key", "ddh.message");
        return new Queue(DEAD_QUEUE, true, false, false, args);
    }*/

}
