package com.ddh.learn.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/8 13:31
 */
@FeignClient("storage-service")
public interface StorageClient {
    /**
     * 更新库存，feign调用
     *
     * @param commodityCode 商品编号
     * @param counts        数量
     * @return 是否成功
     */
    @GetMapping("/storage/{commodityCode}/{counts}")
    String updateStorage(@PathVariable String commodityCode, @PathVariable Integer counts);
}
