package com.ddh.learn.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/28 14:18
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端启动 .......");
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("客户端连接成功: " + accept.getRemoteSocketAddress());
            Thread handler = new Handler(accept);
            handler.start();
        }
    }


    static class Handler extends Thread {

        Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream input = this.socket.getInputStream();
                 OutputStream output = this.socket.getOutputStream()) {
                handle(input, output);
            } catch (Exception e) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }

        private void handle(InputStream input, OutputStream output) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
            writer.write("连接成功\n");
            writer.flush();
            while (true) {
                String content = reader.readLine();
                if ("bye".equals(content)) {
                    writer.write("bye\n");
                    writer.write("服务端断开连接\n");
                    writer.flush();
                    break;
                }
                writer.write("服务端" + content + "\n");
                writer.flush();
            }
        }
    }
}
