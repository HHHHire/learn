package com.ddh.learn.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 1:58
 * @description:
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("msg: " + msg);
        out.writeLong(msg);
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(10);
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.heapBuffer();
        ByteBuf buffer1 = ctx.alloc().buffer();
    }
}
