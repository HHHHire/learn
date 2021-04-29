package com.ddh.learn.product.service.impl;

import com.ddh.learn.product.model.Product;
import com.ddh.learn.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 20:46
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        return Product.builder().id(id).productName("手机").productNum(2).productPrice(6666.66).build();
    }
}
