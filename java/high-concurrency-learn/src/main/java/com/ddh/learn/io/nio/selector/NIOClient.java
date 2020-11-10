package com.ddh.learn.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/10/9 15:21
 */
public class NIOClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(address);

        if (!connect) {
            while (!socketChannel.finishConnect()) {
                System.out.println("还在尝试连接中");
                Thread.sleep(1000);
            }
        }

        String msg = "芜湖，起飞！！！";
        // 将字节数组包装到 ByteBuffer 中
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buffer);
        buffer.flip();
        System.in.read();

    }
}
