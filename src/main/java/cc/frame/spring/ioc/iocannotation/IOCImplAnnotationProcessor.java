package cc.frame.spring.ioc.iocannotation;

import cc.frame.spring.ioc.ServiceIOC;
import cc.frame.spring.ioc.impl.AclaImpl;
import cc.java0.annotation.runtime.Reflect;
import org.apache.poi.ss.formula.functions.T;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author everforcc 2021-09-29
 */
public class IOCImplAnnotationProcessor {

    public static <T extends Object>  T loadClass(final Class<T> clazz) throws Exception {
        final T obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});

        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field.getName());
            Annotation[] annotations = field.getAnnotations();
            for(Annotation annotation:annotations){
                if(annotation.annotationType().isAssignableFrom(IOCImplAnnotation.class)){
                    System.out.println("匹配到了字段注解");
                    // TODO
                    field.set(obj,new AclaImpl());
                }
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        try {

            ServiceIOC serviceIOC = loadClass(ServiceIOC.class);
            serviceIOC.TIOC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
