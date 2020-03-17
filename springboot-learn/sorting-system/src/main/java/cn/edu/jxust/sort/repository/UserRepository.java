package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete sa,st,su from ss_account sa,ss_user su,ss_token st where sa.user_id = su.user_id and st.user_id = su.user_id and su.enterprise_id = ?1", nativeQuery = true)
    Integer deleteByEnterpriseId(String enterpriseId);

    Optional<User> findByUserName(String userName);

    Page<User> findAllByEnterpriseId(String enterpriseId, Pageable pageable);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    Integer deleteByUserId(String userId);

    List<User> findAllByEnterpriseId(String enterpriseId);
}
