package cn.cc.design.single.singleton4;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Singleton {

    private volatile static Singleton singleton;
    private Singleton (){}
    public static Singleton getSingleton() {
        if (singleton == null) {

            // 锁
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }

        }
        return singleton;
    }

}