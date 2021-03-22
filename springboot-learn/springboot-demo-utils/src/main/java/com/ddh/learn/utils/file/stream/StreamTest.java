package com.ddh.learn.utils.file.stream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/20 20:08
 */
public class StreamTest {
    public static void readFile(String filePath) {

        File file = new File(filePath);
        // 缓存的数组
        byte[] tmp = new byte[1024];
        // 每次读取的字节数，如果为 -1 表示没有读到数据
        int byteRead = 0;
        try (InputStream in = new FileInputStream(file);
             FileOutputStream out = new FileOutputStream(file)) {
            while ((byteRead = in.read(tmp)) != -1) {
                out.write(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取字节，并转换成字符
     *
     * @param input 文件流
     * @return
     * @throws IOException
     */
    public static String readAsString(InputStream input) throws IOException {
        int n;
        StringBuilder str = new StringBuilder();
        while ((n = input.read()) != -1) {
            str.append((char) n);
        }
        return str.toString();
    }

    /**
     * 读取压缩文件
     *
     * @param zipPath
     */
    public static void readZipFile(String zipPath) {
        File file = new File(zipPath);
        ZipEntry zipEntry = null;
        try (ZipInputStream input = new ZipInputStream(new FileInputStream(file))) {
            while ((zipEntry = input.getNextEntry()) != null) {
                String name = zipEntry.getName();
                if (!zipEntry.isDirectory()) {
                    int n;
                    while ((n = input.read()) != -1) {
                        //
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readChar(String filePath) {
        File file = new File(filePath);
        char[] tmp = new char[1024];
        int n;
        // FileReader
        try (Reader reader = new FileReader("a")) {
            while ((n = reader.read(tmp)) != -1) {
                System.out.println(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // InputStreamReader，可以将 inputStream 类型的转化为 reader 类型
        try (InputStream input = new FileInputStream(file);
             Reader reader = new InputStreamReader(input)) {
            while ((n = reader.read(tmp)) != -1) {
                System.out.println(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
}
