package cc.java8.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CollectionStreamTest {

    /**
     * https://blog.csdn.net/qq_37131111/article/details/99546357
     */

    private static ArrayList<Demo> demoArrayList = Lists.newArrayList(new Demo(1L,"1L"),new Demo(2L,"SF"));

    public static List<Long> toList(List<Demo> demos){
        //demos.stream().map(Demo::getId).collect(Collectors.toSet());
        return demos.stream().map(Demo::getId).collect(Collectors.toList());
    }

    public static Map<Long,Demo> toMap(List<Demo> demos){
        return demos.stream().collect(Collectors.toMap(Demo::getId,e -> e));
    }

    // TODO Optional 的API文档 Optional,Optional号称NPE终结者
    public static void listFilter(List<Demo> demos){
        String targetName = "SF";
        Demo target = demos.stream()
                    .filter(e -> targetName.equals(e.getName()))
                    .findFirst().orElse(null);
        System.out.println("listFilter:" + target);
    }

    public static void listMatch(List<Demo> demos){
        String targetName = "SFF";
        boolean flagFilter = demos.stream()
                .map(Demo::getName)
                .filter(e -> targetName.equals(e))
                .findAny()
                .isPresent();
        boolean flagMatch = demos.stream()
                .map(Demo::getName)
                .anyMatch(e -> targetName.equals(e));

        System.out.println("flagFilter:" + flagFilter);
        System.out.println("flagMatch:" + flagMatch);
    }

    public static void main(String[] args) {
        // 1.1 list set
        List<Long> ids = toList(demoArrayList);
        ids.forEach(System.out::println);

        // 1.2 map
        Map<Long,Demo> demoMap = toMap(demoArrayList);
        demoMap.forEach((k,v) -> System.out.println(("toMap:" + k + " --- " + v)));

        // 2. filter
        listFilter(demoArrayList);

        // 3.
        listMatch(demoArrayList);
    }

}

@Data
@AllArgsConstructor
class Demo {
    private Long id;
    private String name;
}

