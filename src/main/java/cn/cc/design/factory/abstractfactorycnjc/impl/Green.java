package cn.cc.design.factory.abstractfactorycnjc.impl;

import cn.cc.design.factory.abstractfactorycnjc.Color;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }

}
