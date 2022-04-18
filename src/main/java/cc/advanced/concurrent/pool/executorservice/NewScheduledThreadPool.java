package cc.advanced.concurrent.pool.executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class NewScheduledThreadPool {

    // 创建一个定长线程池，支持定时及周期性任务执行——延迟执行
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) {

        excute(new Runnable() {
            public void run() {
                System.out.println("延迟1秒后每1秒执行一次");
            }
        });

    }

    public static void excute(Runnable runnable){
        scheduledThreadPool.scheduleAtFixedRate(runnable,
                1,
                1,
                TimeUnit.SECONDS); // TimeUnit.SECONDS 第一个参数 延迟时间,第二个参数周期,第三个参数 延迟单位
        //scheduledThreadPool.execute(runnable);
    }

}
