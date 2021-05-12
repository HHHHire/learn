package com.ddh.learn.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/8 11:18
 */
public class MyClient {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup(1))
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                })
    }
}
