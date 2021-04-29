package com.ddh.learn.tcp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/28 14:21
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6666);
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            handler(input, output);
        }
        socket.close();
        System.out.println("客户端断开连接");
    }

    private static void handler(InputStream input, OutputStream output) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            writer.write(scanner.nextLine());
//            writer.newLine();
            writer.flush();
            String response = reader.readLine();
            System.out.println("客户端得到相应: " + response);
            if ("bye".equals(response)) {
                break;
            }
        }
    }
}
