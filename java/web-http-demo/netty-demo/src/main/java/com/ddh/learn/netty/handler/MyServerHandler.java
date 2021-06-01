package com.ddh.learn.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 2:04
 * @description:
 */
public class MyServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器端收到数据: " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端发送数据:" + 111L);
        ctx.writeAndFlush(111L);
    }
}
