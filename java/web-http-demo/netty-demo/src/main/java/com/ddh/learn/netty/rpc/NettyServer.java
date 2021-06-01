package com.ddh.learn.netty.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 9:35
 * @description:
 */
public class NettyServer {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);

        // 服务端启动器，负责组装netty组件
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 两个线程组
        serverBootstrap.group(bossGroup, workerGroup)
                // 服务端通道实现类
                .channel(NioServerSocketChannel.class)
                // 设置线程队列得到连接的个数
                .option(ChannelOption.SO_BACKLOG, 128)
                // 设置保持活动连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 添加的处理器都是给 SocketChannel 而不是 ServerSocketChannel
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new MyServerHandler());

                    }
                });
        System.out.println("....服务器准备好了....");
        // 绑定一个端口并且同步，生成了一个channelFuture对象
        // 启动
        ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();

        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("绑定端口 9999 成功");
            } else {
                System.out.println("端口绑定失败");
            }
        });

        // 对关闭通道进行监听，对有关闭请求的通道进行关闭
        channelFuture.channel().closeFuture().sync();
    }
}
