package cc.java0.generics;

/**
 * 3.泛型接口
 * @author everforcc 2021-09-13
 */
public interface Info<T> {
    T getVar() ; // 定义抽象方法，抽象方法的返回值就是泛型类型
    void setVar(T x);
}
