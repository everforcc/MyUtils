package cc.java8.eg;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamLimitTest {

    public static void main(String[] args) {
        List<String> stringList = skip();

        List<String> resultList = stringList.stream().skip(0).limit(1).collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }

    public static List<String> skip(){
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        stringList.add("e");
        return stringList;
    }

}
