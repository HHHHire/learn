package com.ddh.learn.transactional;

import com.ddh.learn.transactional.entity.Order;
import com.ddh.learn.transactional.entity.TaskLog;
import com.ddh.learn.transactional.repository.TaskRepository;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.Objects;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 9:09
 */
@RocketMQTransactionListener(txProducerGroup = "TEST")
public class TransactionServiceListener implements RocketMQLocalTransactionListener {
    private final TransactionService transactionService;
    private final TaskRepository taskRepository;

    public TransactionServiceListener(TransactionService transactionService, TaskRepository taskRepository) {
        this.transactionService = transactionService;
        this.taskRepository = taskRepository;
    }

    /**
     * 在监听到服务端返回的确认信息后，执行本地事务
     *
     * @param msg 消息
     * @param arg 参数
     * @return Commit or Rollback
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            // 执行本地事务
            transactionService.localTransaction(Objects.requireNonNull(msg.getHeaders().get("TX-ID")).toString(), (Order) arg);
            // 向服务端返回状态
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 在服务端未收到Commit或Rollback确认时，会对该消息回查；检查本地事务的状态
     *
     * @param msg 消息
     * @return Commit or Rollback
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        TaskLog taskLog = taskRepository.findById(Objects.requireNonNull(msg.getHeaders().get("TX-ID")).toString()).orElse(null);
        if (taskLog != null) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
