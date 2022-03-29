package cc.design.design3single.singleton5;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Singleton {

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton (){}

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}