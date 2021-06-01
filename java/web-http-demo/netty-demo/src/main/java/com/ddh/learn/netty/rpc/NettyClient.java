package com.ddh.learn.netty.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.*;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 9:36
 * @description:
 */
public class NettyClient {

    private static MyClientHandler handler;

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 10,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.AbortPolicy());


    public Object getBean(Class<?> serviceClass, String productName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{serviceClass},
                (proxy, method, args) -> {

                    if (handler == null) {
                        initClient();
                    }

                    handler.setPro(productName + args[0]);
                    return executor.submit(handler).get();
                });
    }

    public static void initClient() throws Exception {
        handler = new MyClientHandler();
        Bootstrap bootstrap = new Bootstrap();
        Channel channel = bootstrap.group(new NioEventLoopGroup())
                // 客户端通道实现类
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(handler);
                    }
                })
                // 连接，异步
                .connect("127.0.0.1", 9999)
                // 这里使用同步等待
                .sync()
                // 获取channel对象
                .channel();
        // 写入消息并清空缓存区
//        channel.writeAndFlush(new Date() + " : hello world!");
//        ChannelFuture channelFuture = channel.closeFuture().sync();
    }

}
