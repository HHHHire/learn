package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface CategoryRepository extends JpaRepository<Category, String> {
    Page<Category> findAllByEnterpriseId(String enterpriseId, Pageable pageable);
    Page<Category> findByEnterpriseIdAndDeviceId(String enterpriseId, String deviceId, Pageable pageable);
    Category findByEnterpriseIdAndCategoryId(String enterpriseId, String categoryId);
}
