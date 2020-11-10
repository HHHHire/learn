package com.ddh.learn.concurrency.thread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/30 10:33
 */
public class CLHLock {
    /**
     * 节点
     */
    public static class CLHNode {
        public volatile boolean isLocked = true;
    }

    /**
     * 尾部节点
     */
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> local = new ThreadLocal<>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> updater = AtomicReferenceFieldUpdater
            .newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        // 新建节点
        CLHNode clhNode = new CLHNode();
        local.set(clhNode);
        // 将新建的节点设为尾部节点，并返回上一个旧节点
        CLHNode preNode = updater.getAndSet(this, clhNode);
        if (preNode != null) {
            // 自旋等待上一个旧节点释放锁
            while (preNode.isLocked) {
                System.out.println();
            }
            preNode = null;
            local.set(clhNode);
        }
        // 如果没有旧节点，可以直接获得锁
    }

    public void unlock() {
        // 获取当前线程对应的节点
        CLHNode node = local.get();
        // 通过 CAS 比较当前节点是否为 node 节点，如果是则将节点更新为 null，同时将锁状态置为 false
        if (updater.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        node = null;
    }
}
