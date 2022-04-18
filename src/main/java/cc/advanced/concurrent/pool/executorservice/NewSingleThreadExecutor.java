package cc.advanced.concurrent.pool.executorservice;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class NewSingleThreadExecutor {

    // 创建一个单线程化的线程池
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        excute(new Runnable() {
            @SneakyThrows
            public void run() {
                //结果依次输出，相当于顺序执行各个任务
                System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是:");
                Thread.sleep(1000);
            }
        });
    }

    // 每个调用者必须打印当前的线程号作为日志
    public static void excute(Runnable runnable){
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(runnable);
        }
    }

}
