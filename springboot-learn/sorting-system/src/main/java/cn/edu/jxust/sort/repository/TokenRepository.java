package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface TokenRepository extends JpaRepository<Token, String> {
    @Modifying
    Integer deleteByUserId(String userId);

    Optional<Token> findByUserToken(String token);
}
