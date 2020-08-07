package cn.edu.jxust.springbootdemomongodb.service.impl;

import cn.edu.jxust.springbootdemomongodb.entity.User;
import cn.edu.jxust.springbootdemomongodb.repository.UserRepository;
import cn.edu.jxust.springbootdemomongodb.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/3 15:06
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
