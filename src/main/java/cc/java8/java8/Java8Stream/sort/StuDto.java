package cc.java8.java8.Java8Stream.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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
    public static void main(String[] args) {
        List<StuDto> list = new ArrayList<>();
        list.add(new StuDto(1, "Mahesh"));
        list.add(new StuDto(2, "Suresh"));
        list.add(new StuDto(3, "Nilesh"));
        List<StuDto> slist = list.stream().sorted().collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName()));

    }
}
