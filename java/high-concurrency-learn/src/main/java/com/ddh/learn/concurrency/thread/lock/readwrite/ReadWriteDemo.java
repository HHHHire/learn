package com.ddh.learn.concurrency.thread.lock.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/30 13:59
 */
public class ReadWriteDemo {
    private Map<String, String> map = new HashMap<>();
    private ReadWriteLock readWriteDemo = new ReentrantReadWriteLock();
    private Lock readLock = readWriteDemo.readLock();
    private Lock writeLock = readWriteDemo.writeLock();

    public String get(String key) {
        // 读取数据用读锁
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public String set(String key, String value) {
        // 写入数据用写锁
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteDemo r1 = new ReadWriteDemo();
        ReadWriteDemo r2 = new ReadWriteDemo();
        r1.set("hello", "world");
        r2.set("hello","world1");
        System.out.println(r1.get("hello"));
        System.out.println(r2.get("hello"));

    }
}
