package cn.edu.jxust.springbootmybatisdemo.mapper;

import cn.edu.jxust.springbootmybatisdemo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: ddh
 * @date: 2019/10/31  22:43
 * @description:
 */
@Repository
public interface UserMapper {
    User getName(String id);
}
