package cc.java0.generics.impl;

import cc.java0.generics.Info;

/**
 * @author guokailong 2021-09-13
 */
class InfoImpl implements Info<String> {   // 定义泛型接口的子类
    private String var ;                // 定义属性
    public InfoImpl(String var){        // 通过构造方法设置属性内容
        this.setVar(var) ;
    }
    @Override
    public void setVar(String var){
        this.var = var ;
    }
    @Override
    public String getVar(){
        return this.var ;
    }
}
class Tinfo{
    public static void main(String[] args) {
        InfoImpl i = new InfoImpl("var");
        System.out.println(i.getVar()) ;
    }
}