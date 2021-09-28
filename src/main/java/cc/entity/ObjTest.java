package cc.entity;

import com.alibaba.fastjson.JSON;

/**
 * @author everforcc
 * @data 2021/9/2 0002
 */
public class ObjTest {

    public static void main(String[] args) {
        notSerialize();
    }

    /**
     * 对象属性为null不会序列化
     */
    public static void notSerialize(){
        ObjectField obj = new ObjectField();
        obj.setaLong(123L);
        System.out.println(JSON.toJSON(obj));
    }


}
