package com.ddh.practicedemo;

import com.auth0.jwt.interfaces.Claim;
import com.ddh.practicedemo.util.TokenUtil;
import com.ddh.practicedemo.util.UUIDUtil;
import jdk.nashorn.internal.parser.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeDemoApplicationTests {

    @Autowired
    TokenUtil tokenUtil;

    @Test
    public void contextLoads() {

    }

    /**
     * 测试 UUID
     */
    @Test
    public void testUUID() {
        System.out.println(UUIDUtil.getUUID());
    }

    /**
     * 测试 token
     */
    @Test
    public void testToken() {
        Map<String, String> map = new HashMap<>();

        map.put("username", "zhangsan");
        String token = tokenUtil.createJwt(map);
        System.out.println(token);

        Claim claim = tokenUtil.getClaim(token, "username");
        System.out.println(claim.asString());
    }
}
