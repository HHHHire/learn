package com.ddh.learn.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 17:17
 * @description:
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder被调用");
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);

        MessageProtocol msg = MessageProtocol.builder().len(len).content(bytes).build();
        out.add(msg);
    }
}
