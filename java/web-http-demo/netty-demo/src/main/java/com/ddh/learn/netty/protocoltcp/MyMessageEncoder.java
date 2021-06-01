package com.ddh.learn.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 17:15
 * @description:
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
