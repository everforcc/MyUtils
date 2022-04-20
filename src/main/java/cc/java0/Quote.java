package cc.java0;

import cc.sysentity.ObjectField;

import java.util.ArrayList;
import java.util.List;

public class Quote {

    void q1(){
        // 声明一个对象,并赋值
        ObjectField objectField = new ObjectField();
        objectField.setaString("a");

        List<ObjectField> objectFieldList = new ArrayList<ObjectField>();
        objectFieldList.add(objectField);

        // 引用1
        ObjectField objectField1=objectFieldList.get(0);
        System.out.println(objectField1.getaString());

        // 引用2
        ObjectField objectField2=objectFieldList.get(0);
        // 修改值
        objectField2.setaString("b");

        // 引用1的值被修改
        System.out.println(objectField1.getaString());
        // map的值被修改
        System.out.println(objectFieldList.get(0).getaString());
        // 原始对象
        System.out.println(objectField.getaString());

        // 结论
        // 两个引用和map其实都指向了同一块内存
    }

}
