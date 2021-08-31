package cc.design.factory.abstractfactorycnjc.impl;

import cc.design.factory.abstractfactorycnjc.Shape;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
