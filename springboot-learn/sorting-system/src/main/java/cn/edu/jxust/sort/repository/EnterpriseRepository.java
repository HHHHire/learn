package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface EnterpriseRepository extends JpaRepository<Enterprise, String> {
    Optional<Enterprise> findByEnterpriseName(String enterpriseName);

    @Override
    Page<Enterprise> findAll(Pageable pageable);

    @Modifying
    @Transactional
    Integer deleteByEnterpriseId(String enterpriseId);
}
