package com.ddh.learn.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/7 22:01
 */
public class NioServer {
    /**
     * 1. 当客户端连接时，通过 ServerSocketChannel 得到 SocketChannel
     * 2. selector 监听 select 方法，返回有事件发生的通道个数
     * 3. 将 socketChannel 注册到 selector 上
     * 4. 在有事件发生时，通过 selectionKey 获取到对应的通道，buffer
     */
    public static void main(String[] args) throws Exception {
        // 获取到 serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 获取选择器
        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 注册监听连接事件，accept 表示连接事件，connect 则可能是无连接的
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 判断有无客户端连接
            if (selector.selectNow() == 0) {
                System.out.println("没有客户端连接");
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // 连接事件
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 注意也要设置非阻塞
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println("客户端连接成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (selectionKey.isReadable()) {
                    // 读事件，获取到发生事件的通道
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    // 获取到对应的buffer，这个buffer可以说是服务端的buffer
                    ByteBuffer attachment = (ByteBuffer)selectionKey.attachment();
                    try {
                        // 从通道里读数据到buffer中
                        channel.read(attachment);
                        System.out.println("from 客户端: " + new String(attachment.array()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // 移除selectionKey
                iterator.remove();
            }
        }

    }
}
