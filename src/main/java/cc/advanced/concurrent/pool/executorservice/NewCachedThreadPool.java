package cc.advanced.concurrent.pool.executorservice;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author c.c.
 * @date 2021/3/1
 */
public class NewCachedThreadPool {

    // 创建一个可缓存线程池
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     *
     * @param runnable
     */
    @SneakyThrows
    public static void excute(Runnable runnable){


        // 执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
            Thread.sleep(1000);
            cachedThreadPool.execute(runnable);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        excute(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());
            }
        });
    }

}
