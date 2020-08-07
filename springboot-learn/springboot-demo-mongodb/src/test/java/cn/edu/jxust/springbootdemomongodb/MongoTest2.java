package cn.edu.jxust.springbootdemomongodb;

import cn.edu.jxust.springbootdemomongodb.entity.User;
import cn.edu.jxust.springbootdemomongodb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/3 15:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest2 {
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        User user = User.builder().id("001")
                .username("zhangsan")
                .password("123").build();
        userService.save(user);
    }
}
