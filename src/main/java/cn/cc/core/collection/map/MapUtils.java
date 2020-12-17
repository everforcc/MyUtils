package cn.cc.core.collection.map;

import com.google.common.collect.Maps;

import java.util.*;

/**
 * Yukino
 * 2020/6/12
 */
public class MapUtils {

    private static Map<String,String> map = new HashMap<String,String>();
    static {
        map.put("a", "aa");
        map.put("b", "bb");
        //初始化类型, 这种有问题，不建议用
        Map<String, String> mapInit = new HashMap<String, String>()
        {{
            put("HR.xlsx", "HR_BUDGET");
        }};
    }

    //遍历map, 几种遍历方式
    void forEach1(){
        /**
         * 最常见也是大多数情况下用的最多的，一般在键值对都需要使用
         */
        for(Map.Entry<String, String> entry : map.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);
        }
    }
    void forEach2(){
        //在for循环中遍历key或者values，一般适用于只需要map中的key或者value时使用，在性能上比使用entrySet较好；
        // 也可以在取出key后再取出value
        for(String key : map.keySet()){
            System.out.println(key);
        }
        for(String value : map.values()){
            System.out.println(value);
        }
    }

    void forEach3() {
        // 使用 iterator
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, String> entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }
    }

    public static void main(String[] args) {
        // google 的jar包?
        Map<String, Integer> hashMap = Maps.newHashMap();
        Map<String, Integer> treeMap = Maps.newTreeMap();
        // 如果需要顺序的那么就使用这个
        Map<String, Integer> linkedHashMap = Maps.newLinkedHashMap();
        System.out.println("--------------test hashMap");
        testMap(hashMap);
        System.out.println("--------------test treeMap");
        testMap(treeMap);
        System.out.println("--------------test linkedHashMap");
        testMap(linkedHashMap);
    }

    private static void testMap(Map<String, Integer> map) {
        map.put("asd", 1);
        map.put("2das", 2);
        map.put("3das", 3);
        map.put("4das", 4);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }


}
