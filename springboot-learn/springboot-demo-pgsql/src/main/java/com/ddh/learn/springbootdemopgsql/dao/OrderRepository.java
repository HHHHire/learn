package com.ddh.learn.springbootdemopgsql.dao;

import com.ddh.learn.springbootdemopgsql.model.po.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/28 17:17
 * @description:
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
