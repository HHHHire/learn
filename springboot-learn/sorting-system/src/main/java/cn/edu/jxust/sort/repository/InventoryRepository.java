package cn.edu.jxust.sort.repository;


import cn.edu.jxust.sort.entity.po.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: ddh
 * @data: 2020/1/8 15:28
 * @description
 **/
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Page<Inventory> findByEnterpriseId(String enterpriseId, Pageable pageable);

    Optional<Inventory> findByEnterpriseIdAndCategoryId(String enterpriseId, String categoryId);
}
