package com.ddh.learn.order.fallback;

import com.ddh.learn.order.feign.ProductFeignClient;
import com.ddh.learn.order.model.Product;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:28
 */
@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductFeignClient> {
    @Override
    public ProductFeignClient create(Throwable throwable) {
        return new ProductFeignClient() {
            @Override
            public Product getProduct(Long id) {
                return new Product(id, "出错啦", 2, 6666.00);
            }
        };
    }
}
