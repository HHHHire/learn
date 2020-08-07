package com.ddh.learn.rocketmq;

import com.ddh.learn.rocketmq.model.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 10:13
 */
@RestController
public class SendController {
    private final RocketMQTemplate template;

    public SendController(RocketMQTemplate template) {
        this.template = template;
    }

    @GetMapping("/send")
    public String sendMsg(@RequestParam String msg) {
        Message build = Message.builder().id("001").msg(msg).build();

        template.convertAndSend("test-topic", build);
        
        return "发送成功";
    }
}
