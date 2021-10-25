package jdk;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gxf on 2017/6/21.
 */
public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Task task = new Task();
        timer.schedule(task, new Date(), 1000);
    }
}

class Task extends TimerTask{

    @Override
    public void run() {
        System.out.println("Do work...");
    }
}
