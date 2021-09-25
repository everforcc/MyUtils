package cc.java0.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author guokailong 2021-09-23
 */
public class ThreadIC implements Callable {
    @Override
    public Object call() throws Exception {
        return "ThreadIC";
    }
    public static void main(String[] args){
        FutureTask<String> futureTask = new FutureTask<String>(new ThreadIC());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
