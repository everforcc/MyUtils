package cc.java0.annotation.base;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author guokailong 2021-09-08
 */
public class AnnotationTest {

    public static void main(String[] args) throws Exception {

        // 获取类上的注解
        Class<Demo> clazz = Demo.class;
        MyAnnotation annotationOnClass = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnClass.getValue());

        // 获取成员变量上的注解
        Field name = clazz.getField("name");
        MyAnnotation annotationField = name.getAnnotation(MyAnnotation.class);
        System.out.println(annotationField.getValue());

        // 获取hello方法上的注解
        Method hello = clazz.getMethod("hello",(Class<?>[]) null);
        MyAnnotation annotationMethod = hello.getAnnotation(MyAnnotation.class);
        System.out.println(annotationMethod.getValue());

        // 获取hello方法上的注解
        Method defaultMethod = clazz.getMethod("defaultMethod",(Class<?>[]) null);
        MyAnnotation annotationDefaultMethod = defaultMethod.getAnnotation(MyAnnotation.class);
        System.out.println(annotationDefaultMethod.getValue());


    }



}
