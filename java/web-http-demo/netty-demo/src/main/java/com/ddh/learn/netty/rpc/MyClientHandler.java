package com.ddh.learn.netty.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 9:46
 * @description:
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private String pro;
    private ChannelHandlerContext context;
    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接成功");
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到数据");
        result = msg.toString();
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call1");
        context.writeAndFlush(pro);
        wait();

        System.out.println("call2");
        return result;
    }

    public void setPro(String productName) {
        System.out.println("set pro");
        pro = productName;
    }
}
