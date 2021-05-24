package com.ddh.learn.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/23 18:50
 * @description:
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("myCodec", new HttpServerCodec());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast("myServerHandler", new MyServerHandler());
    }
}
