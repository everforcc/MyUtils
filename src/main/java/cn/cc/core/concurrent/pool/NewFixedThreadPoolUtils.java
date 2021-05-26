package cn.cc.core.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class NewFixedThreadPoolUtils {

    // 创建一个可重用固定个数的线程池，以共享的无界队列方式来运行这些线程。
    public static ExecutorService defaultFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        // 创建一个可重用固定个数的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        // 打印正在执行的缓存线程信息
                        System.out.println(Thread.currentThread().getName()
                                + "正在被执行");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void excute(Runnable runnable){
        // 创建一个可重用固定个数的线程池

        // 创建全局的总线程
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            fixedThreadPool.execute(runnable);
        }
    }

}
