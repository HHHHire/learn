package com.ddh.learn.transactional.repository;

import com.ddh.learn.transactional.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 9:03
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
