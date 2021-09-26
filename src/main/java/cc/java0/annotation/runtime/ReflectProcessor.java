package cc.java0.annotation.runtime;

import java.lang.reflect.Method;

/**
 * @author everforcc 2021-09-08
 * 注解处理器
 */
public class ReflectProcessor {

    public void parseMethod(final Class<?> clazz) throws Exception {
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            final Reflect reflect = method.getAnnotation(Reflect.class);
            if (null != reflect) {
                method.invoke(obj, reflect.name());
            }
        }
    }


}
