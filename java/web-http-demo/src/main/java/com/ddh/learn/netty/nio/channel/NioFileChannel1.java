package com.ddh.learn.netty.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/5 1:38
 */
public class NioFileChannel1 {
    public static void main(String[] args) throws IOException {
        String str = "hello 中国";
        try (FileOutputStream outputStream = new FileOutputStream(new File("d:\\test.txt"));) {
            FileChannel fileChannel = outputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(str.getBytes());
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        }

    }
}
