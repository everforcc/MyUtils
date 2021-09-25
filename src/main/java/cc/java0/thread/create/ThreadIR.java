package cc.java0.thread.create;

/**
 * @author guokailong 2021-09-23
 */
public class ThreadIR implements Runnable{

    static volatile int i = 0;

    @Override
    public void run() {
        testSynchronized();
    }

    public void test() {
        // 数据量小的时候没问题，但是数据量大，就会出现原子性问题
        for(int j=0; j<100000; j++) {
            System.out.println("j:" + j + " i:"+ ++i);
        }
    }

    public synchronized void testSynchronized() {
        // 数据量小的时候没问题，但是数据量大，就会出现原子性问题
        for(int j=0; j<100000; j++) {
            System.out.println("j:" + j + " i:"+ ++i);
        }
    }

    public static void main(String[] args) {
        ThreadIR runableTest1 = new ThreadIR();
        ThreadIR runableTest2 = new ThreadIR();
        ThreadIR runableTest3 = new ThreadIR();
        ThreadIR runableTest4 = new ThreadIR();
        ThreadIR runableTest5 = new ThreadIR();
        ThreadIR runableTest6 = new ThreadIR();
        ThreadIR runableTest7 = new ThreadIR();
        ThreadIR runableTest8 = new ThreadIR();
        ThreadIR runableTest9 = new ThreadIR();
        ThreadIR runableTest10 = new ThreadIR();
        ThreadIR runableTest11 = new ThreadIR();
        ThreadIR runableTest12 = new ThreadIR();
        new Thread(runableTest1).start();
        //new Thread(runableTest1).start();
        new Thread(runableTest2).start();
        new Thread(runableTest3).start();
        new Thread(runableTest4).start();
        new Thread(runableTest5).start();
        new Thread(runableTest6).start();
        new Thread(runableTest7).start();
        new Thread(runableTest8).start();
        new Thread(runableTest9).start();
        new Thread(runableTest10).start();
        new Thread(runableTest11).start();
        new Thread(runableTest12).start();
    }
}
