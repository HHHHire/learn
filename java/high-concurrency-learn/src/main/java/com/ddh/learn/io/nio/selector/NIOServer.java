package com.ddh.learn.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/10/9 15:21
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocketChannel.bind(address);
        // 非阻塞
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 返回0，相当于没有事件
            if (selector.select(3000) == 0) {
                System.out.println("服务器等了3秒，没有连接");
                continue;
            }
            // 如果有事件>0，获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 连接事件
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 把socketChannel注册到selector中，监听读事件，并绑定一个缓存区
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                // 读事件，在客户端写入通道就会触发
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取关联的缓冲区
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println(new String(buffer.array()));
                }
                iterator.remove();
            }
        }
    }
}
