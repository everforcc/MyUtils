package cc.design.design2factory.staticfactory;

import cc.design.design2factory.*;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class ShapeFactory1 implements FactoryMethod {

    @Override
    public Shape create(String type) {
        switch(type) {
            case "Circle": return new Circle();
            case "Square": return new Square();
            case "Triangle": return new Triangle();
            default: throw new BadShapeCreation(type);
        }
    }

    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory1());
    }

}
