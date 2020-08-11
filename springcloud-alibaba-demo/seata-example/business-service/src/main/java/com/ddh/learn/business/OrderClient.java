package com.ddh.learn.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/8 13:29
 */
@FeignClient("order-service")
public interface OrderClient {
    /**
     * 创建订单，feign调用
     *
     * @param userId        用户id
     * @param commodityCode 商品编号
     * @param counts         数量
     * @return 是否成功
     */
    @PostMapping(value = "/order", produces = "application/json")
    String createOrder(@RequestParam("userId") String userId,
                       @RequestParam("commodityCode") String commodityCode,
                       @RequestParam("counts") Integer counts);
}
