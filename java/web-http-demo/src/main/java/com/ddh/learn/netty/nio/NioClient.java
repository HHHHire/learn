package com.ddh.learn.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/8 0:25
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("客户端还在尝试连接，但不会阻塞，可以做其他工作");
            }
        }
        // 发送数据
        String message = "hello world ，你好";
        // 将要发送的数据用wrap包含，他会根据数据大小自动调节，不用设置长度
        // 这个buffer可以说是客户端的buffer
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        // 发送数据，将buffer写入到channel中
        socketChannel.write(buffer);
        System.in.read();

    }
}
