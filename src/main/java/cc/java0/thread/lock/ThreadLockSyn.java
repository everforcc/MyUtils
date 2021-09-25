package cc.java0.thread.lock;

/**
 * @author guokailong 2021-09-23
 */
public class ThreadLockSyn extends Thread{

    private int threadNo;
    private String lock;

    public ThreadLockSyn(int threadNo, String lock) {
        this.threadNo = threadNo;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 1; i < 10000; i++) {
                System.out.println("No." + threadNo + ":" + i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String lock = new String("lock");
        for (int i = 1; i < 10; i++) {
            new ThreadLockSyn(i, lock).start();
            Thread.sleep(1);
        }
    }

}
