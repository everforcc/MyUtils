package cc.java0.reflection.base;

import java.lang.reflect.Method;

/**
 * @author guokailong 2021-09-09
 */
public class TestClassLoad {
    public static void main(String[] args) throws Exception {
        // 类路径
        Class<?> clz = Class.forName("cc.java0.reflection.base.A");
        // 实例化
        Object o = clz.newInstance();
        // 调用方法，传参数类型
        Method m = clz.getMethod("methodA", String.class);

        for (int i = 0; i < 16; i++) {
            m.invoke(o, Integer.toString(i));
        }
    }
}
