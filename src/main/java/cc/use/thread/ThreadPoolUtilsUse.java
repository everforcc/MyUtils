package cc.use.thread;

import cc.advanced.concurrent.pool.ThreadPoolUtils;

/**
 * @author c.c.
 * @date 2021/3/2
 */
public class ThreadPoolUtilsUse implements Runnable{

    private String data;

    private int num;

    public ThreadPoolUtilsUse(String data, int num) {
        this.data = data;
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是:" + data + " >>> " + num);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TThread{
    public static void main(String[] args) throws InterruptedException {
        int i = 0 ;
        System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是:" );
        for(int j=0;j<20;j++) {
            ThreadPoolUtils.excuteFixed(new ThreadPoolUtilsUse("data0", i++));
            ThreadPoolUtils.excuteFixed(new ThreadPoolUtilsUse("data1", i++));
            Thread.sleep(100);
        }

        System.out.println("暂停10分钟");
        Thread.sleep(1000 );//* 60 * 15

        ThreadPoolUtils.excuteFixed(new ThreadPoolUtilsUse("data1", i++));
    }
}