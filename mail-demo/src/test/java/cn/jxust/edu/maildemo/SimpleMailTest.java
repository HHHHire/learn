package cn.jxust.edu.maildemo;

import cn.jxust.edu.maildemo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: ddh
 * @date: 2019/9/20  17:20
 * @description:
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleMailTest {
    @Autowired
    private MailService mailService;

    @Test
    public void sendMail() {
        mailService.sendMail("tset", "发送邮件", "dengdh167@163.com");
        System.out.println("213123123");
    }
}
