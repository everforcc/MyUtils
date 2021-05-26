package cn.cc.design.observer;

/**
 * @author c.c.
 * @date 2021/3/25
 */
public abstract class Observer {

    protected Subject subject;
    public abstract void update();

}
