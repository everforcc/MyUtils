package cc.java0.reflection.last;



import cc.entity.ObjectField;

import java.lang.reflect.Field;

public class Method {

    public void beanCopy(ObjectField A, ObjectField B) throws Exception{
        Class Aclass = A.getClass();// 得到EcPayMentMainDto类对象
        Field[] Afields = Aclass.getDeclaredFields();//得到属性集合
        Class Bclass = B.getClass();// 得到EcPayMentMainDtoForJSON类对象
        Field[] Bfields = Bclass.getDeclaredFields();//得到属性集合
        for (Field Afield : Afields) {
            Afield.setAccessible(true); // 设置属性是可以访问的
            labe:for (Field Bfield : Bfields) {
                Bfield.setAccessible(true); // 设置属性是可以访问的
                if(Afield.getName().equals(Bfield.getName())){
                    if (Afield.getGenericType().toString().equals(Bfield.getGenericType().toString())) {
                        Bfield.set(B, Afield.get(A));
                    }
                    break labe;
                }
            }
        }
    }
}
