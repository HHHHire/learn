package cn.edu.jxust.redislogin;

import cn.edu.jxust.redislogin.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RedisLoginApplicationTests {

    @Autowired
    private LoginService loginService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        String cardId = "1001";
//        loginService.listQueueInit(cardId);
        List<String> waitList = loginService.listQueueWait(cardId);
        System.out.println("--------------等待-------------------");
        for (String s : waitList) {
            System.out.println(s);
        }
        loginService.listQueueTouch(cardId);
        System.out.println("--------------开始执行----------------");
        List<String> succList = loginService.listQueueSucc(cardId);
        System.out.println("-----------------成功-----------------");
        for (String s : succList) {
            System.out.println(s);
        }
    }
}
