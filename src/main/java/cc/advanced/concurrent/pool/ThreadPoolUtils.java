package cc.advanced.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class ThreadPoolUtils {

    /**
     * 四种线程池的方式
     * 每个调用者必须在方法内输出当前线程号到日志，方便定位问题
     */

    // 线程数
    private static int threadSize = Runtime.getRuntime().availableProcessors();
    private static final long awaitTime = 5 * 1000;

    // 创建一个可缓存线程池
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    // 创建一个可重用固定个数的线程池，以共享的无界队列方式来运行这些线程。
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);
    // 创建一个定长线程池，支持定时及周期性任务执行——延迟执行
    public static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(threadSize);
    // 创建创建一个单线程化的线程池
    public static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    // 线程太多 不建议使用
    public static void excuteCache(Runnable runnable){
        // 创建一个可缓存线程池

        // 线程池为无限大，当执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
        try {
            // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cachedThreadPool.execute(runnable);
    }

    // 还行
    public static ExecutorService excuteFixed(Runnable runnable){
        // 创建一个可重用固定个数的线程池
        fixedThreadPool.execute(runnable);
        return fixedThreadPool;
    }

    // 设置延迟,可以爬虫时使用，避免被封
    public static void excuteSchedule(Runnable runnable){
        //延迟1秒执行
        /*scheduledThreadPool.schedule(new Runnable() {
            public void run() {
               System.out.println("延迟1秒执行");
            }
        }, 1, TimeUnit.SECONDS);*/

        // 延迟1秒后每3秒执行一次
        // TimeUnit.SECONDS 第一个参数 延迟索酒,第二个参数间隔,第三个参数 延迟单位
        scheduledThreadPool.scheduleAtFixedRate(runnable, 1, 3, TimeUnit.SECONDS);
    }

    // 初始延迟 周期 间隔类型
    public static void excuteSchedule(Runnable runnable,int initialDelay,int period,TimeUnit timeUnit){
        // TimeUnit.SECONDS 第一个参数 延迟索酒,第二个参数间隔,第三个参数 延迟单位
        scheduledThreadPool.scheduleAtFixedRate(runnable, initialDelay, period, timeUnit);
    }

    public static void excuteSingle(Runnable runnable){
        // 创建一个单线程化的线程池
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(runnable);
        }
    }

    public static void shutdown(ExecutorService executorService){
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)){
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
