package com.ddh.learn.netty.codec2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/8 10:58
 */
public class MyServer {
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
                        // 使用 proto 解码器
                        ch.pipeline().addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
                        ch.pipeline().addLast(new MyServerHandler());

                    }
                });
        System.out.println("....服务器准备好了....");
        // 绑定一个端口并且同步，生成了一个channelFuture对象
        // 启动
        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("绑定端口 8888 成功");
            } else {
                System.out.println("端口绑定失败");
            }
        });

        // 对关闭通道进行监听，对有关闭请求的通道进行关闭
        channelFuture.channel().closeFuture().sync();
    }
}
