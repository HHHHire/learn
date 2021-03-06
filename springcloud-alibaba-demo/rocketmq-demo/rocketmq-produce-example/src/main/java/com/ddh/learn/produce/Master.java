package com.ddh.learn.produce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/19 0:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Master {
    private String name;
    private Integer age;
}
