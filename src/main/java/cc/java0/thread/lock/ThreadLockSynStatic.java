package cc.java0.thread.lock;

/**
 * @author guokailong 2021-09-23
 */
public class ThreadLockSynStatic extends Thread{

    private int threadNo;
    private String lock;

    public ThreadLockSynStatic(int threadNo) {
        this.threadNo = threadNo;
    }

    public static synchronized void abc(int threadNo) {
        for (int i = 1; i < 10000; i++) {

            System.out.println("No." + threadNo + ":" + i);
        }
    }

    @Override
    public void run() {
        abc(threadNo);
    }

    public static void main(String[] args) throws Exception {

        for (int i = 1; i < 20; i++) {
            new ThreadLockSynStatic(i).start();
            Thread.sleep(1);
        }
    }

}
