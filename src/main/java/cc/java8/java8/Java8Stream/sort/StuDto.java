package cc.java8.java8.Java8Stream.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author everforcc 2021-09-14
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

        /**
         *
         * 1.1sorted()：它使用自然顺序对流的元素进行排序。元素类必须实现Comparable接口。
         * list.stream().sorted() .stream().sorted(); 自然升序
         * list.stream().sorted(Comparator.reverseOrder()); 降序
         *
         * 1.2 sorted(Comparator<? super T> comparator):这里我们创建一个Comparator使用lambda表达式的实例。我们可以按升序和降序对流元素进行排序。
         * 按自然升序对集合进行排序
         * list.stream().sorted(Comparator.comparing(Student::getAge));
         * 自然序降序使用Comparator提供reverseOrder()方法
         * list.stream().sorted(Comparator.reverseOrder());
         */

        //1.

        //2.
        //listSort();
        //3.
        //setSort();
        //4.
        mapSort();
    }

    private static void limit(List<StuDto> list,int currentPage,int pageSize){
        // 排序后进行分页,页码，页面size
        list.stream().skip((currentPage-1)*pageSize).limit(pageSize).
                collect(Collectors.toList());

    }

    //4. map
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

        System.out.println(" 多条件排序--- ");
        slist = list.stream().sorted(Comparator.comparing(StuDto::getName).reversed().thenComparing(StuDto::getId)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));
    }



}
