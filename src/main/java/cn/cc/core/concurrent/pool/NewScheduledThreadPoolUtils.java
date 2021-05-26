package cn.cc.core.concurrent.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class NewScheduledThreadPoolUtils {

    // 创建一个定长线程池，支持定时及周期性任务执行——延迟执行

    public static void main(String[] args) {
        //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //延迟1秒执行
                 /*scheduledThreadPool.schedule(new Runnable() {
                     public void run() {
                        System.out.println("延迟1秒执行");
                     }
                 }, 1, TimeUnit.SECONDS);*/


        //延迟1秒后每3秒执行一次
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("延迟1秒后每3秒执行一次");
            }
        }, 1, 1, TimeUnit.MINUTES); // TimeUnit.SECONDS 第一个参数 延迟索酒,第二个参数间隔,第三个参数 延迟单位

    }

    public static void excute(){

    }

}
