package com.ddh.learn.netty.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/7 22:01
 */
public class NioServer {
    public static void main(String[] args) throws Exception {
        // 获取到 serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        serverSocketChannel.register(selector, )

    }
}
