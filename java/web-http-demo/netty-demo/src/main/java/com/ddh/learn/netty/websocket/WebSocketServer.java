package com.ddh.learn.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/28 1:41
 * @description:
 */
public class WebSocketServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列得到连接的个数
//                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 基于http协议，使用http的编码解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 是以块方式写，添加 ChunkedWriteHandler
                            pipeline.addLast(new ChunkedWriteHandler());
                            // http数据在传输过程中是分段的，HttpObjectAggregator，就是将多段聚合
                            // 所以浏览器在发送大量数据时， 就会发送多次http请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // websocket 的数据是以 帧(frame)的形式传递
                            // WebSocketFrame 下有六个字类
                            // 浏览器请求时 ws://localhost:6666/hello 表示请求的 url
                            // WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            // 自己的业务
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            System.out.println("服务端启动了");
            // 让它一直卡在这
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
