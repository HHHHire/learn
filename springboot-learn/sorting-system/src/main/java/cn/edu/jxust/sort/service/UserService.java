package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: ddh
 * @data: 2020/1/4 20:09
 * @description
 **/
public interface UserService {
    Integer deleteUserByEnterpriseId(String enterpriseId);

    User getUserByUserName(String userName);

    User createUser(User user);

    Page<User> getAllUser(String enterpriseId, Pageable pageable);

    User getUserById(String userId);

    Integer deleteUserByUserId(String userId);

    List<User> getUserByEnterpriseId(String enterpriseId);
}
