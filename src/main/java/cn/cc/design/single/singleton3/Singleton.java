package cn.cc.design.single.singleton3;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Singleton {

    private static Singleton instance = new Singleton();
    private Singleton (){}
    public static Singleton getInstance() {
        return instance;
    }

}
