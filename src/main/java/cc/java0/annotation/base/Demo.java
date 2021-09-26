package cc.java0.annotation.base;

/**
 * @author everforcc 2021-09-08
 */
@MyAnnotation(getValue = "annotation on class")
public class Demo {

    @MyAnnotation(getValue = "annotation on field")
    public String name;

    @MyAnnotation(getValue = "annotation on method")
    public void hello() {}

    @MyAnnotation() // 故意不指定getValue
    public void defaultMethod() {}
}