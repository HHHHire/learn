package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCRUD {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("张三");
        user.setAddress("浙江");
        user.setSex("男");

        userDao.saveUser(user);

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(49);
        user.setUsername("张三");
        user.setAddress("瑞安");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);

    }

    @Test
    public void testDelete(){
        userDao.deleteUser(49);
    }

    @Test
    public void testfindByName(){
        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testTotalUser(){
        Integer totalUser = userDao.findTotalUser();
        System.out.println(totalUser);

    }
}
