package cc.java0.thread.lock;

/**
 * @author guokailong 2021-09-23
 */
public class ThreadLockErr extends Thread{
    private int threadNo;

    public ThreadLockErr(int threadNo) {
        this.threadNo = threadNo;
    }

    @Override
    public synchronized void run() {
        for (int i = 1; i < 10000; i++) {
            System.out.println("No." + threadNo + ":" + i);
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            new ThreadLockErr(i).start();
            Thread.sleep(1);
        }
    }

}
