package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByAdminName(String adminName);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    Integer deleteByAdminId(String adminId);
}
