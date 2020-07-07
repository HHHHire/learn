package com.ddh.learn.springbootdemodozer;

import com.ddh.learn.springbootdemodozer.model.Phone;
import com.ddh.learn.springbootdemodozer.model.Teacher;
import com.ddh.learn.springbootdemodozer.model.User;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/7 15:11
 * @description
 */
@SpringBootTest(classes = SpringbootDemoDozerApplication.class)
@RunWith(SpringRunner.class)
public class DozerXmlTest {

    @Autowired
    private Mapper mapper;

    /**
     * xml配置方式：需要先把xml配置注入到容器中，
     */
    @Test
    public void test(){
        Set<Phone> set = new HashSet<>();
        set.add(new Phone("1"));
        set.add(new Phone("2"));
        set.add(new Phone("3"));
        User user = User.builder().username("zhangsan")
                .age(12)
                .phone(set)
                .build();
        Teacher map = mapper.map(user, Teacher.class);
        System.out.println(map);
    }
}
