package com.ddh.learn.transactional;

import com.ddh.learn.transactional.entity.Order;
import com.ddh.learn.transactional.entity.TaskLog;
import com.ddh.learn.transactional.repository.OrderRepository;
import com.ddh.learn.transactional.repository.TaskRepository;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 消息发送端
 *
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 17:29
 * todo: 暂未测试
 */
@SuppressWarnings("unused")
@Service
public class TransactionService {
    private final TaskRepository taskRepository;
    private final OrderRepository orderRepository;

    private final RocketMQTemplate rocketMQTemplate;

    public TransactionService(TaskRepository taskRepository, RocketMQTemplate mqTemplate, OrderRepository orderRepository) {
        this.taskRepository = taskRepository;
        this.rocketMQTemplate = mqTemplate;
        this.orderRepository = orderRepository;
    }

    /**
     * 发送半事务消息
     */
    public void sendTransactionMsg() {
        String taskId = UUID.randomUUID().toString();
        Order order = Order.builder().id("001")
                .userName("zhang")
                .pay(99.0).build();
        // 发送半事务消息
        rocketMQTemplate.sendMessageInTransaction("TEST", "just for test",
                MessageBuilder.withPayload(order).setHeader("TX-ID", taskId).build(), order);
    }

    /**
     * 本地事务
     */
    void localTransaction(String taskId, Order order) {
        orderRepository.save(order);

        TaskLog taskLog = TaskLog.builder().taskId(taskId)
                .taskMsg("事务测试")
                .createTime(new Date()).build();
        taskRepository.save(taskLog);
    }

}
