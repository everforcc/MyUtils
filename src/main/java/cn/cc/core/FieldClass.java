package cn.cc.core;

import java.lang.reflect.Field;

public class FieldClass {


    public void getObjectValue(Object object){
        Class<?> cla=object.getClass();
        Field[] fields=cla.getDeclaredFields();
        for (Field field:fields){

            /**
             * 属性名
             */
            System.out.println("属性名:getName:"+field.getName());

            /**
             * 属性类型
             */
            System.out.println("type1:"+field.getGenericType());

            /**
             *
             */
            System.out.println("type2:"+field.getGenericType().toString());
            System.out.println(":toString:"+field.toString());
            System.out.println(":toGenericString:"+field.toGenericString());
        }


    }

}
