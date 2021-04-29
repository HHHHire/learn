package com.ddh.learn.order.feign;

import com.ddh.learn.order.fallback.ProductClientFallbackFactory;
import com.ddh.learn.order.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:26
 */
@FeignClient(value = "product-server", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductFeignClient {
    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long id);
}
