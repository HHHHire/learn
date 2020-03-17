package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUserNameAndUserPwd(String userName, String userPwd);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    Integer deleteByUserId(String userId);
}
