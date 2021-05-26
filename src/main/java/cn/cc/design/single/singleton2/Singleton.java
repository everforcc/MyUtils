package cn.cc.design.single.singleton2;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Singleton {

    private static Singleton instance;
    private Singleton (){}
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
