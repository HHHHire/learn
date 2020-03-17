package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author: ddh
 * @data: 2020/1/8 15:59
 * @description
 **/
public interface CategoryService {

    Page<Category> getCategory(String enterpriseId, String deviceId, Pageable pageable);

    Category getCategoryById(String enterpriseId, String categoryId);
}
