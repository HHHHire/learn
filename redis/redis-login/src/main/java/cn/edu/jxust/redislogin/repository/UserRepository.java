package cn.edu.jxust.redislogin.repository;

import cn.edu.jxust.redislogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: ddh
 * @date: 2019/10/29  19:47
 * @description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
