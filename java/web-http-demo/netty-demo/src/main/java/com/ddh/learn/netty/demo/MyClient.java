package com.ddh.learn.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/8 11:18
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        Channel channel = bootstrap.group(new NioEventLoopGroup())
                // 客户端通道实现类
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new MyClientHandler());
                    }
                })
                // 连接，异步
                .connect("127.0.0.1", 8888)
                // 这里使用同步等待
                .sync()
                // 获取channel对象
                .channel();
        // 写入消息并清空缓存区
//        channel.writeAndFlush(new Date() + " : hello world!");
        ChannelFuture channelFuture = channel.closeFuture().sync();
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("关闭成功");
            }
        });

    }
}
