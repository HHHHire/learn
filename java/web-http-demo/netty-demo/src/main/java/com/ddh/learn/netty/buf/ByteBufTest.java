package com.ddh.learn.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/26 1:08
 * @description:
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        System.out.println(buffer.capacity());

        for (int i = 0; i < 10; i++) {
            buffer.readByte();
        }

        // 这里就会越界错误
//        buffer.readByte();

        // 常用api
        // 偏移量
        buffer.arrayOffset();
        // 可读字节数
        buffer.readableBytes();
        // 截取
        buffer.getCharSequence(0, 4, CharsetUtil.UTF_8);
    }
}
