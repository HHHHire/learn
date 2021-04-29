package com.ddh.learn.redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.asm.Advice;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/16 15:18
 */
@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private String name;
    private Long id;
}
