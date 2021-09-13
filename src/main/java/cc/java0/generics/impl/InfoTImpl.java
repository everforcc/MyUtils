package cc.java0.generics.impl;

import cc.java0.generics.Info;

/**
 * @author guokailong 2021-09-13
 */
public class InfoTImpl<T> implements Info<T> {

    private T var ;              // 定义属性
    public InfoTImpl(T var){     // 通过构造方法设置属性内容
        this.setVar(var) ;
    }

    @Override
    public T getVar() {
        return var;
    }

    @Override
    public void setVar(T x) {
        this.var = x;
    }
}
class TInfoTImpl{
    public static void main(String[] args) {
        InfoTImpl<String> i = new InfoTImpl<String>("name");
        System.out.println(i.getVar()) ;
    }
}