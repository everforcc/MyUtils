package cc.java0.annotation.base2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    // 8种基本
    int intValue();
    long longValue();
    String name();
    CityEnum cityName();
    // class类型
    Class<?> clazz();
    // 注解类型
    MyAnnotation2 annotation2();

    // 一维数组
    int[] intValueArray();
    String[] names();
}

@interface MyAnnotation2{}

enum CityEnum{
    HENAN,
    ZHENGZHOU;
}