package com.ddh.learn.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/7 22:49
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private final static int PORT = 6667;

    public GroupChatServer() {
        try {
            // 得到选择器
            selector = Selector.open();
            // 得到监听通道
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            listenChannel.configureBlocking(false);
            // 注册
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            // 没有传入时间，就是阻塞
            int count = selector.select();
            if (count > 0) {
                // 有事件处理
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    // 拿到key
                    SelectionKey selectionKey = keyIterator.next();
                    // 监听到连接事件
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = listenChannel.accept();
                        socketChannel.configureBlocking(false);
                        // 将该通道注册到selector
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress() + " 上线了 ");
                    }
                    // 监听到读事件
                    if (selectionKey.isReadable()) {
                        readData(selectionKey);
                    }
                    // 删除 key
                    keyIterator.remove();
                }
            } else {
                System.out.println("等待...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端信息
     */
    public void readData(SelectionKey key) {
        // 取导关联的channel
        SocketChannel channel = null;
        try {
            // 得到channel
            channel = (SocketChannel) key.channel();
            // 创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                // 把缓存区的数据转为字符串
                String msg = new String(buffer.array());
                System.out.println("from 客户端： " + msg);
                // 向其他的客户端转发消息（去掉自己）
                sendInfoToOtherClients(msg, channel);
            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了..");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) {
        System.out.println("服务器转发消息中...");
        // 遍历所有注册到selector上的socketChannel，并排除self
        selector.keys().forEach(key -> {
            // 通过key取出相应的 socketChannel
            Channel targetChannel = key.channel();
            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                // 转型
                SocketChannel dest = (SocketChannel) targetChannel;
                // 将msg存储到buffer
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                // 将buffer的数据写入到通道
                try {
                    dest.write(wrap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
