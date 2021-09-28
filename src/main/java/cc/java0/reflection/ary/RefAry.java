package cc.java0.reflection.ary;

import java.lang.reflect.Array;

/**
 * @author everforcc 2021-09-09
 */
public class RefAry {

    public static void main(String[] args) {
        try {
            testArray();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testArray() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls,25);
        //往数组里添加内容
        Array.set(array,0,"hello");
        Array.set(array,1,"Java");
        Array.set(array,2,"fuck");
        Array.set(array,3,"Scala");
        Array.set(array,4,"Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array,6));
    }

}
