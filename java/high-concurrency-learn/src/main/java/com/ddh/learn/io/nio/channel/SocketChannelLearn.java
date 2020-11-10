package com.ddh.learn.io.nio.channel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/10/9 14:49
 */
public class SocketChannelLearn {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocketChannel.bind(address);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(buffer) != -1) {
                System.out.println(new String(buffer.array()));
                buffer.clear();
            }
        }
    }
}
