package com.ddh.learn.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 2:05
 * @description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端拿到数据:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送数据");
        ctx.writeAndFlush(1231L);
        // 这里发送16个字节的数据
        // 这个处理器的下一个handler是MyLongToByteEncoder，父类是MessageToByteEncoder，会根据类型判断是否调用encoder方法
        // 显然，这里的数据类型是string，所以就不会调用MyLongToByteEncoder
//        ctx.writeAndFlush(Unpooled.copiedBuffer("aabbaabbaabbaabb", CharsetUtil.UTF_8));
    }
}
