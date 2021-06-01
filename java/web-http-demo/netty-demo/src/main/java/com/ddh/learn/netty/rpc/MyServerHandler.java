package com.ddh.learn.netty.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 10:03
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端读取数据");
        System.out.println(msg.toString());
        UserService userService = new UserServiceImpl();
        String hello = userService.hello(msg.toString());
        ctx.writeAndFlush(hello);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端通道可用");
    }
}
