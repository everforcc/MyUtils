package cn.cc.design.factory.abstractfactorycnjc.impl;

import cn.cc.design.factory.abstractfactorycnjc.Color;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }

}
