package com.ddh.learn.netty.protocoltcp;

import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 1:25
 * @description:
 */
@Data
@Builder
public class MessageProtocol {
    private int len;
    private byte[] content;
}
