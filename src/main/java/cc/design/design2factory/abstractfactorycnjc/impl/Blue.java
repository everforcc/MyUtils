package cc.design.design2factory.abstractfactorycnjc.impl;

import cc.design.design2factory.abstractfactorycnjc.Color;

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
