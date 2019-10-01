package cn.edu.jxust.serviceprovider.service;

import cn.edu.jxust.serviceprovider.mapper.UserMapper;
import cn.edu.jxust.serviceprovider.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ddh
 * @Date: 2019/9/30 16:21
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }
}
