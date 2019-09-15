package cn.etu.jxust.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: ddh
 * @Date: 2019/9/15 18:02
 * @Description:
 */
public class AppConsumer {
    private static final String url = "tcp://192.168.0.107:61616";
    private static final String queueName = "queue-test";

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
        // 创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收到" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
