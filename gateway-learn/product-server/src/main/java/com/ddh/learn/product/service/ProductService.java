package com.ddh.learn.product.service;

import com.ddh.learn.product.model.Product;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 20:45
 */
public interface ProductService {
    Product getProductById(Long id);
}
