package cc.advanced.concurrent.pool.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class NewFixedThreadPool {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {

        excute(new Thread());
    }

    /**
     * 创建一个可重用固定个数的线程池，以共享的无界队列方式来运行这些线程。
     * @param runnable
     */
    public static void excute(Runnable runnable){
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            fixedThreadPool.execute(runnable);
        }
    }

}
