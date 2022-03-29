package cc.design.design20observer;

/**
 * @author c.c.
 * @date 2021/3/25
 */
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
        //observers.remove(observer);
        System.out.println("加入通知");
    }

    public void cancelAttach(Observer observer){
        //observers.add(observer);
        observers.remove(observer);
        System.out.println("取消通知");
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
