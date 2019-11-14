package learn;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ddh
 * @date 2019/8/23 20:23
 * @description
 **/
public class Demo2 {
    @Test
    public void test01() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int n = i;
            Thread.sleep(1000);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n);
                }
            });
        }
    }
}
