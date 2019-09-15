package cn.etu.jxust.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: ddh
 * @Date: 2019/9/15 18:01
 * @Description:
 */
public class AppProduct {
    private static final String url = "tcp://192.168.0.107:61616";
    private static final String queueName = "topic-test";

    public static void main(String[] args) throws JMSException {
        // 创建connectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 创建connection
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建一个目标
        Destination destination = session.createQueue(queueName);
        // 创建一个生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            // 创建消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            producer.send(textMessage);
            System.out.println("发送消息" + textMessage.getText());
        }
        //关闭连接
        connection.close();
    }
}
