package learn;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author ddh
 * @date 2019/8/23 17:27
 * @description
 **/
public class Demo1 {
    @Test
    public void threadPoolExecutorTest1() throws InterruptedException {
        // 核心线程5，最大数量10，无界限队列，超出核心线程数量的线程存活时间5秒
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        testCommon(threadPoolExecutor);
    }

    /**
     * 核心线程5，最大数量10，等待队列最大是3的线程池，也就是最大容纳13个任务，超出核心线程数量的线程存活时间5秒，指定拒绝策略
     */
    @Test
    public void threadPoolExecutorTest2() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), (r, executor) -> System.out.println("有任务被拒绝执行了"));
        testCommon(threadPoolExecutor);
        /**
         * 5个任务直接分配执行
         * 3个任务进入等待队列
         * 队列不够用，临时加开五个线程来执行任务（5秒钟没干活就销毁）
         * 队列和线程池都满了，剩下两个任务没资源了，被拒绝执行
         * 任务执行，5秒钟后，如无任务执行，销毁临时创建的5个线程
         */
    }

    /**
     * 核心线程5，最大数量5，无界限队列，超出核心线程数量的线程存活时间5秒，其实效果和第一个一样，在无界限队列情况下最大数量就没用了
     */
    @Test
    public void threadPoolExecutorTest3() throws InterruptedException {
        // 和Executors.newFixedThreadPool(int nThreads) 一样的
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        testCommon(threadPoolExecutor);
    }

    /**
     * 核心线程0，最大数量Integer.MAX_VALUE，同步队列，超出核心线程数量的线程存活时间60秒
     */
    @Test
    public void threadPoolExecutorTest4() throws InterruptedException {
        // 和Executors.newCachedThreadPool() 一样的
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
        testCommon(threadPoolExecutor);
    }

    private void testCommon(ThreadPoolExecutor threadPoolExecutor) throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("开始执行" + n);
                    Thread.sleep(3000L);
                    System.out.println("执行结束" + n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("任务提交成功" + n);
        }
        // 查看线程数量，查看队列等待数量
        Thread.sleep(500L);
        System.out.println("当前线程池数量为：" + threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待数量为：" + threadPoolExecutor.getQueue().size());
        // 等待15秒，查看线程数量，查看队列等待数量
        Thread.sleep(15000L);
        System.out.println("当前线程池数量为：" + threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待数量为：" + threadPoolExecutor.getQueue().size());
    }
}
