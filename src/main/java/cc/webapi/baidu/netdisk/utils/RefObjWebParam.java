package cc.webapi.baidu.netdisk.utils;

import cc.core.string.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author everforcc 2021-10-12
 */
public class RefObjWebParam {

    public static String getField(Object obj){
        Class<?> clazz = obj.getClass();
        StringBuffer stringBuffer = new StringBuffer("?");
        // 多个字段
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            try {
                field.setAccessible(true);

                Object value = field.get(obj);
                if(Objects.nonNull(value)){
                    String name = field.getName();
                    System.out.println(name);
                    stringBuffer.append(name + "=" + value + "&");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.substring(0,stringBuffer.length()-1);
    }

}
