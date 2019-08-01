package com.example.taskdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDemoApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    /**
     * 发送简单邮件
     */
    @Test
    public void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("test");  //标题
        simpleMailMessage.setText("hello world");
        simpleMailMessage.setTo("dengdh167@163.com");
        simpleMailMessage.setFrom("1670360527@qq.com");

        ExecutorService exec = Executors.newCachedThreadPool();
        javaMailSender.send(simpleMailMessage);


//        System.out.println("send message");
//        exec.execute(() -> {
//            System.out.println("start send");
//            for (int i = 0; i < 100; i++) {
//            }
//            System.out.println("end send");
//        });
//        if (!exec.isShutdown()) {
//            exec.shutdown();
//        }
    }

    /**
     * 发送复杂邮件
     */
    @Test
    public void test02() throws MessagingException {
        //1.创建一个复杂的消息邮件
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

        //2.邮件设置
        mimeMessageHelper.setSubject("test");
        mimeMessageHelper.setText("<b style='color:blue'>hello world</b>", true);
        mimeMessageHelper.setTo("dengdh167@163.com");
        mimeMessageHelper.setFrom("1670360527@qq.com");

        mimeMessageHelper.addAttachment("image.png", new File("D:\\ChromeCoreDownloads\\20180710155531286.png"));
        javaMailSender.send(mimeMailMessage);
    }

}
