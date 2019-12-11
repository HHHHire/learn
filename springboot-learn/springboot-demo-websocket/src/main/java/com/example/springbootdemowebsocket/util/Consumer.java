package com.example.springbootdemowebsocket.util;


import com.example.springbootdemowebsocket.config.RabbitMQConfig;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

/**
 * @author: ddh
 * @data: 2019/12/2 16:02
 * @description
 **/
@Component
@Slf4j
public class Consumer {
    private static String msg;
    public String recieve() {
        // 通过连接工厂拆功能键鑫的连接和mq建立连接
        msg = "";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.100.88.162");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 设置虚拟机
        factory.setVirtualHost("/");

        Connection connection = null;
        Channel channel;
        try {
            // 建立新连接
            connection = factory.newConnection();
            channel = connection.createChannel();
            // 申明队列，在此声明队列是为了防止没有队列
            channel.queueDeclare(RabbitMQConfig.TOPIC_QUEUE, true, false, false, null);
            // 声明一个交换机
            channel.exchangeDeclare(RabbitMQConfig.TOPIC_EXCHANGE, BuiltinExchangeType.DIRECT);
            // 进行交换机和队列绑定
            // 参数 String queue, String exchange, String routingKey
            /*
             * 1、queue：队列名称
             * 2、exchange：交换机
             * 3、routingKey：路由
             */
            channel.queueBind(RabbitMQConfig.TOPIC_QUEUE, RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY);
            // 实现消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                /**
                 * 当接收到消息后，此方法将被调用
                 * @param consumerTag 消费者标签，用来表示消费者，在监听队列时设置channel,basicConsume
                 * @param envelope 信封
                 * @param properties 消息属性
                 * @param body 消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    // 获取交换机
//                    String exchange = envelope.getExchange();
                    // 消息id,mq在channel中用来表示消息的id，可用于确认消息已接收
//                    long deliveryTag = envelope.getDeliveryTag();
//                    System.out.println("exchange: " + exchange + " deliveryTag: " + deliveryTag);
                    // 消息内容
                    String message = new String(body, StandardCharsets.UTF_8);
//                    System.out.println(message);

                    log.info("收到消息:" + message + "时间: " + LocalDateTime.now());
                    msg = message;
                }
            };
            // 监听队列
            // 参数：String queue, boolean autoAck, Consumer callback
            /*
             * 参数明细
             * 1、queue：队列名称
             * 2、autoAck：自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为true则表示会自动恢复mq
             * 3、callback：消费方法，当消费者接收消息要执行的方法
             */
            channel.basicConsume(RabbitMQConfig.TOPIC_QUEUE, true, defaultConsumer);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            System.out.println("end");
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return msg;
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.recieve();
    }
}
