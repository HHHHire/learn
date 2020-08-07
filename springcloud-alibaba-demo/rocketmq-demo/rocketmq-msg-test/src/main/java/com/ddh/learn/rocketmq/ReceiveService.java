package com.ddh.learn.rocketmq;

import com.ddh.learn.rocketmq.model.Message;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 10:17
 */
@RocketMQMessageListener(
        topic = "test-topic",
        consumerGroup = "consumers1",
        consumeMode = ConsumeMode.CONCURRENTLY,
        messageModel = MessageModel.BROADCASTING
)
@Service
public class ReceiveService implements RocketMQListener<Message> {

    @Override
    public void onMessage(Message message) {
        System.out.println("收到消息: " + message.getMsg());
    }
}
