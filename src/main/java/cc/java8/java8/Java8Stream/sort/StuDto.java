package cc.java8.java8.Java8Stream.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author guokailong 2021-09-14
 */
@Data
@AllArgsConstructor
public class StuDto implements Comparable<StuDto>  {

    private int id;
    private String name;

    // 从大到小
    @Override
    public int compareTo(StuDto o) {
        if (this.id > o.getId()) {
            return -1; //正整数是大于
        } else if (this.id < o.getId()) {
            return 1;//负整数是小于
        } else {
            return 0; //0为等于
        }
    }
}
class StuDoTest{
    private static StuDto a = new StuDto(1,"a");
    private static StuDto b = new StuDto(2,"b");
    private static StuDto c = new StuDto(3,"c");
    public static void main(String[] args) {

        //2.
        //listSort();
        //3.
        //setSort();
        //4.
        mapSort();
    }

    private static void mapSort(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "c");
        map.put(2, "b");
        map.put(3, "a");

        System.out.println("---Value排序---");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));

        System.out.println("---Key排序---");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));
    }

    //3. set
    private static void setSort(){
        Set<StuDto> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);

        System.out.println(" 定义顺序--- ");
        set.stream().sorted().forEach(e ->
                System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

        System.out.println(" 定义反序--- ");
        set.stream().sorted(Comparator.reverseOrder()).forEach(e ->
                System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

        System.out.println(" 未定义顺序--- ");
        set.stream().sorted(Comparator.comparing(StuDto::getName)).forEach(e ->
                System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

        System.out.println(" 未定义反序--- ");
        set.stream().sorted(Comparator.comparing(StuDto::getName).reversed()).forEach(e ->
                System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

    }

    // 1.语法
    // 2.list
    private static void listSort(){
        List<StuDto> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);

        System.out.println(" 定义顺序--- ");
        List<StuDto> slist = list.stream().sorted().collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

        System.out.println(" 定义反序--- ");
        slist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

        System.out.println(" 未定义顺序--- ");
        slist = list.stream().sorted(Comparator.comparing(StuDto::getName)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

        System.out.println(" 未定义反序--- ");
        slist = list.stream().sorted(Comparator.comparing(StuDto::getName).reversed()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));
    }



}
