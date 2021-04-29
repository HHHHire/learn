package com.ddh.learn.product.controller;

import com.ddh.learn.product.model.Product;
import com.ddh.learn.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 20:50
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
}
