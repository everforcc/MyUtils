package cc.advanced.concurrent.pool;

import lombok.SneakyThrows;

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

    /**
     * 线程太多 不建议使用
     * @param runnable
     */
    @SneakyThrows
    public static void excuteCache(Runnable runnable){
        // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
        Thread.sleep(1000);
        cachedThreadPool.execute(runnable);
    }

    /**
     * 还行
     * @param runnable
     * @return
     */
    public static ExecutorService excuteFixed(Runnable runnable){
        // 创建一个可重用固定个数的线程池
        fixedThreadPool.execute(runnable);
        return fixedThreadPool;
    }

    /**
     * 设置延迟,可以爬虫时使用，避免被封
     * @param runnable
     */
    public static void excuteSchedule(Runnable runnable){
        excuteSchedule(runnable, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 初始延迟 周期 间隔类型
     * @param runnable
     * @param initialDelay
     * @param period
     * @param timeUnit
     */
    public static void excuteSchedule(Runnable runnable,int initialDelay,int period,TimeUnit timeUnit){
        // TimeUnit.SECONDS 第一个参数 延迟索酒,第二个参数间隔,第三个参数 延迟单位
        scheduledThreadPool.scheduleAtFixedRate(runnable, initialDelay, period, timeUnit);
    }

    /**
     * 创建一个单线程化的线程池
     * @param runnable
     */
    public static void excuteSingle(Runnable runnable){

        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(runnable);
        }
    }

    /**
     * 超时的时候向线程池中所有的线程发出中断(interrupted)。
     * @param executorService
     */
    @SneakyThrows
    public static void shutdown(ExecutorService executorService){
        executorService.shutdown();
        if(!executorService.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)){
            executorService.shutdownNow();
        }
    }

}
