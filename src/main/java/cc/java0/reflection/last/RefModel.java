package cc.java0.reflection.last;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 补充完整，
 * 作用
 * 根据反射获取所有的字段名，值，进行匹配对应
 */
public class RefModel {

    private static void doField(Object obj, Object obj2) throws InstantiationException, InvocationTargetException {
        try {
            Field[] properties = obj.getClass().getDeclaredFields();
            Field[] field2 = obj2.getClass().getDeclaredFields();
            for (Field p : properties) {

                boolean accessible = p.isAccessible();
                p.setAccessible(true);
                String fieldName1 = p.getName();
                // boolean类型的isMen的get方法名就是isMen
                String strGet = p.getType().equals(Boolean.class) || p.getType().equals(boolean.class) ? fieldName1
                        : ("get" + fieldName1.substring(0, 1).toUpperCase()
                        + fieldName1.substring(1, fieldName1.length()));
                Method methodGet = obj.getClass().getDeclaredMethod(strGet);
                Object object = methodGet.invoke(obj);
                for (Field p2 : field2) {
                    p2.setAccessible(true);
                    //两个对象名字相同（如果两个对象名字有差异，则可以转换进行匹配）
                    if (p.getName().equals(p2.getName())) {
                        if (returnBoolean(p)) {
                            doField(object, p2.getType().newInstance());
                        } else {
                            if (boolean.class.equals(p2.getType()) || Boolean.class.equals(p2.getType())) {
                                p2.set(obj2, "1".equals(object) ? true : false);
                            } else {
                                p2.set(obj2, object);
                            }
                            System.out.println(p2.getName() + ":" + p2.get(obj2));
                        }
                    }
                    p.setAccessible(accessible);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    //判断是否是基本数据类型或者是String、Date（可能不全面，可能还有其他类型）
    public static boolean returnBoolean(Field f) {
        Class t = f.getType();
        if (String.class.equals(t) || Date.class.equals(t) || byte.class.equals(t) || Byte.class.equals(t)
                || short.class.equals(t) || Short.class.equals(t) || float.class.equals(t) || Float.class.equals(t)
                || long.class.equals(t) || Long.class.equals(t) || int.class.equals(t) || Integer.class.equals(t)
                || double.class.equals(t) || Double.class.equals(t) || boolean.class.equals(t)
                || Boolean.class.equals(t) || char.class.equals(t) || Character.class.equals(t)) {
            return false;
        }
        return true;
    }

}
