package cc.java0.generics;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 5.Class<T>类传递及泛型数组
 * @author everforcc 2021-09-14
 */
public class ClassT {

    // 解析json常用到
    public static <T> List<T> parseJson(String response,Class<T> object){
        List<T> modelList = JSON.parseArray(response, object);
        return modelList;
    }

    //定义
    public static <T> T[] fun1(T...arg){  // 接收可变参数
        return arg ;            // 返回泛型数组
    }
    //使用
    public static void main(String args[]){
        Integer i[] = fun1(1,2,3,4,5,6) ;

        Integer[] result = fun1(i) ;
    }

}
