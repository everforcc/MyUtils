package cc.java0.annotation.base2;

/**
 * @author guokailong 2021-09-08
 */

@MyAnnotation(
        intValue = 1, longValue = 0L, name = "",
        cityName = CityEnum.HENAN,
        clazz = Demo.class,
        annotation2 = @MyAnnotation2,
        intValueArray = {1,2},
        names = "zs" // 如果数组的元素只有一个可以不写花括号
        // 如果注解属性只有一个，并且叫 value那么可以不用指定属性名
)
public class Demo {
}
