package cc.java0.generics.impl;

import cc.java0.generics.Info;

/**
 * @author guokailong 2021-09-13
 */
public class InfoTKUImpl<T,K,U> implements Info<U> {
    private U var ;
    private T x; // 定义但未使用
    private K y;
    public InfoTKUImpl(U var){        // 通过构造方法设置属性内容
        this.setVar(var) ;
    }
    public void setVar(U var){
        this.var = var ;
    }
    public U getVar(){
        return this.var ;
    }
}
