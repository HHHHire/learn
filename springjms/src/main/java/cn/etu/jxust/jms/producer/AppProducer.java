package cn.etu.jxust.jms.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService producerService = (ProducerService) context.getBean("producerService");
        for (int i = 0; i < 100; i++) {
            producerService.sendMessage("test" + i);
        }
    }
}
