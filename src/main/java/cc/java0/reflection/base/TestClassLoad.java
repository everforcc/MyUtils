package cc.java0.reflection.base;

import java.lang.reflect.Method;

/**
 * @author guokailong 2021-09-09
 */
public class TestClassLoad {
    public static void main(String[] args) throws Exception {
        // 类路径,class的静态方法
        Class<?> clazz = Class.forName("cc.java0.reflection.base.A");
        // 直接获取对象的class
//        Class<?> clazzint = int.class;
//        Class<?>  clazzinteger = Integer.TYPE;
        // 调用某个对象的 getClass()
//        String str = new String();
//        Class<?> clazzstr = str.getClass();


        // 实例化
        Object obj = clazz.newInstance();
        // 调用方法，传参数类型
        //Method[] method = clazz.getMethod("methodA", String.class);
        Method[] method = clazz.getDeclaredMethods();
        //System.out.println(method.length);
        for(int i=0;i < method.length;i++){
            System.out.println(method[i].getName());
            method[i].setAccessible(true);
            method[i].invoke(obj," 123 ");
        }
//        method[0].setAccessible(true);
//        method[0].invoke(obj," 123 ");
        /*for (int i = 0; i < 16; i++) {
            method.invoke(obj, Integer.toString(i));
        }*/
    }
}
