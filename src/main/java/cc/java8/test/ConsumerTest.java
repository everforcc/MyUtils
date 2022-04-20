package cc.java8.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    public static void forEach(final Consumer<Object> consumer) {
        List<Object> objectList = new ArrayList<>();
        objectList.add("aaa");
        objectList.add("bbb");
        objectList.add("ccc");
        objectList.forEach(consumer::accept);
    }

    public static void strConcat(Object str){
        System.out.println(str + ":abc");
    }

    public static void main(String[] args) {
        forEach(e -> strConcat(e));
    }

}
