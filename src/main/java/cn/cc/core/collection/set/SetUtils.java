package cn.cc.core.collection.set;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Yukino
 * 2020/6/18
 */
public class SetUtils {

    /**
     *   add有返回值booleanle 类型
     */

    Set<Integer> test = new TreeSet<>();
    int a = 1;
    int b = 8;
    int c = 3;

    void forEach1(){
        test.add(a);
        test.add(b);
        test.add(c);
        //遍历集合test   利用foreach遍历          //输出结果：1   3   8
        for (Integer value : test) {
            System.out.print(value+" ");
        }
        //利用Iterator实现遍历
        Iterator<Integer> value = test.iterator();
        while (value.hasNext()) {
            int s = value.next();
            System.out.print(s+" ");
        }                                //输出结果：1   3   8
    }

    void forEach2(){
        test.add(a);
        test.add(b);
        test.add(c);
        //遍历集合test   利用foreach遍历          //输出结果：1   3   8
        for (Integer value : test) {
            System.out.print(value+" ");
        }
    }

    void forEach3(){
        //利用Iterator实现遍历
        Iterator<Integer> value = test.iterator();
        while (value.hasNext()) {
            int s = value.next();
            System.out.print(s+" ");
        }//输出结果：1   3   8
    }


}
