package cn.cc.core.collection.map;

import cn.cc.entity.ObjectField;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Yukino
 * 2020/6/12
 */
public class MapUtils {

    static {

        //初始化类型, 这种有问题，不建议用
        Map<String, String> map = new HashMap<String, String>()
        {{
            put("HR.xlsx", "HR_BUDGET");
        }};

        // 另外一种简单有效的方法：
        ArrayList<String> friends = new ArrayList<String>(Arrays.asList("a","b","c"));

    }

    Map<Map<Integer,Integer>,String> map ;
    Map<Integer,Integer> key = new HashMap<>();

    //遍历map, 几种遍历方式
    void forEach1(){
        /**
         * 最常见也是大多数情况下用的最多的，一般在键值对都需要使用
         */
        Map <String,String>map = new HashMap<String,String>();
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
        for(Map.Entry<String, String> entry : map.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);
        }
    }
    void forEach2(){
        //在for循环中遍历key或者values，一般适用于只需要map中的key或者value时使用，在性能上比使用entrySet较好；
        Map <String,String>map = new HashMap<String,String>();
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
//key
        for(String key : map.keySet()){
            System.out.println(key);
        }
//value
        for(String value : map.values()){
            System.out.println(value);
        }
    }

    void forEach3() {
        /*Iterator<Entry<String, String>> entries = map.entrySet().iterator();
        while(entries.hasNext()){
            Entry<String, String> entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }*/
    }

    void forEach4() {
        Map <String,String>map = new HashMap<String,String>();
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
        //通过键找值遍历，这种方式的效率比较低，因为本身从键取值是耗时的操作；
        for(String key : map.keySet()){
            String value = map.get(key);
            System.out.println(key+":"+value);
        }
    }

    public static void main(String[] args) {

    }

}
