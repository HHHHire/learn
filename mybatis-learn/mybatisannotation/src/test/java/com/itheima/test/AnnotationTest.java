package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AnnotationTest {
    @Test
    public void findAll() throws IOException {
        // 获取输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 根据SqlSessionFactory生成一个SqlSession
        SqlSession session = factory.openSession();
        // 使用SqlSession获取DAO代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        // 释放资源
        session.close();
        in.close();

    }
}
