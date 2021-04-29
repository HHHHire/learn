package com.ddh.learn.netty.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/5 1:45
 */
public class NioFileChannel2 {
    public static void main(String[] args) throws IOException {
        File file = new File("test1.txt");
        FileInputStream input = new FileInputStream(file);
        // 从文件输入流获取通道
        FileChannel inputChannel = input.getChannel();
        FileOutputStream output = new FileOutputStream(new File("test2.txt"));
        // 从文件输出流获取通道
        FileChannel outputChannel = output.getChannel();

        // 创建一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        // 如果这里不指定大小
        while (true) {
            
        }




        // 把输入流通道的数据读取到缓冲区中
        inputChannel.read(buffer);
        buffer.flip();
        // 把缓冲区中的数据写入到输入流通道中
        outputChannel.write(buffer);

        outputChannel.close();
        output.close();
        inputChannel.close();
        input.close();
    }
}
