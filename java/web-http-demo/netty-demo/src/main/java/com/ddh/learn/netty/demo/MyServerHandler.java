package com.ddh.learn.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/16 16:01
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取客户端发送的数据
     * @param ctx 上下文对象，含有管道pipeline，通道channel，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("server ctx = " + ctx);
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端发送的是: " + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址是: " + ctx.channel().remoteAddress());
        // 如果是耗时的任务，就把他添加到TaskQueue中，异步执行，虽然使用的还是同一个线程
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("耗时任务2", CharsetUtil.UTF_8));
        });
        // 再来一个任务添加到TaskQueue中，但是，这得等到上面的任务执行完，才能执行这个
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("耗时任务3", CharsetUtil.UTF_8));
        });
        // 再来一个定时任务，延时5秒执行，添加到ScheduledTaskQueue中
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("定时任务4", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);
    }

    /**
     * 数据读写完毕
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写入到缓存，并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
