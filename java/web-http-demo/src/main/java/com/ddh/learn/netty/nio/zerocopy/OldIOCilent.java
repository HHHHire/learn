package com.ddh.learn.netty.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/10 10:26
 */
public class OldIOCilent {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8888);
        String fileName = "F:\\tmp\\dgpnr_pro_dy_exp_20210219.dmp";
        FileInputStream input = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();
        while ((readCount = input.read(buffer)) > 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送字节数：" + total + " , 耗时: " + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        input.close();
        socket.close();
    }
}
