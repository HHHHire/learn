package com.ddh.learn.springbootdemodozer;

import com.ddh.learn.springbootdemodozer.model.Teacher;
import com.ddh.learn.springbootdemodozer.model.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/7 14:16
 * @description
 */
@SpringBootTest
public class DozerTest {

    /**
     * 注解方式：dozer自动进行类型转化，如果属性名称不同，则需要指定
     */
    @Test
    public void test1() {
        Mapper mapper = new DozerBeanMapper();


//        User user = User.builder().username("zhangsan")
//                .age(12)
//                .address("shanghai").build();
//        Teacher map = mapper.map(user, Teacher.class);
//        System.out.println(map);
    }
}
