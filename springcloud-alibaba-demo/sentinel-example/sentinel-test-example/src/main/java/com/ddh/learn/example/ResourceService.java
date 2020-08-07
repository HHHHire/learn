package com.ddh.learn.example;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * sentinel资源点异常处理
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 14:02
 */
@Service
public class ResourceService {
    @SentinelResource(
            value = "msg",
            blockHandler = "blockHandler",
            fallback = "fallback"
    )
    public String message() {
        int i = 1;
        if (i++ % 3 == 0) {
            throw new RuntimeException();
        }
        return "that's it";
    }

    /**
     * BlockException进入
     */
    public String blockHandler(BlockException e) {
        return "被限流了" + e;
    }

    /**
     * Throwable进入
     */
    public String fallback(Throwable t) {
        return "有异常" + t;
    }
}
