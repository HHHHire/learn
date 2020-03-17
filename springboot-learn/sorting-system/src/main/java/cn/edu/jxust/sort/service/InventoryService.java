package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: ddh
 * @data: 2020/1/8 16:00
 * @description
 **/
public interface InventoryService {
    Page<Inventory> getInventory(String enterpriseId, Pageable pageable);

    Inventory getInventoryByCategoryId(String enterpriseId, String categoryId);
}
