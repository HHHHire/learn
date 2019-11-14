package cn.edu.jxust;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author: ddh
 * @date: 2019/10/26  22:52
 * @description:
 */
public class ThreadTest {
    @Test
    public void test1() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return null;
            }
        };
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, queue);


    }
}
