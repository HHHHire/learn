package com.ddh.learn.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/10/9 14:15
 * Buffer 缓冲区
 */
public class BufferLearn {
    public static void main(String[] args) {
        String msg = "芜湖，起飞!!";
        byte[] bytes = msg.getBytes();
        // 创建堆（heap）内存缓冲区 实际创建了 HeapByteBuffer，这是在 jvm 中的，受限于jvm，没有特殊要求就使用这种
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 创建直接缓冲区 DirectByteBuffer，这是直接在 os 系统上的，没有限制，存在时间长
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        // 写入缓冲
        buffer.put(bytes);
        // 切换为读模式，读写切换
        buffer.flip();

        byte[] temp = new byte[bytes.length];
        int i = 0;
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            temp[i] = b;
            i++;
        }
        System.out.println(new String(temp));
    }
}
