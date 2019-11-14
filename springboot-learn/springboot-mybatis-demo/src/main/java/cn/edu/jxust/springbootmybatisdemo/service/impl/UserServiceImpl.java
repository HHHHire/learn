package cn.edu.jxust.springbootmybatisdemo.service.impl;

import cn.edu.jxust.springbootmybatisdemo.entity.User;
import cn.edu.jxust.springbootmybatisdemo.mapper.UserMapper;
import cn.edu.jxust.springbootmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ddh
 * @date: 2019/10/31  22:45
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getName(String id) {
        return userMapper.getName(id);
    }
}
