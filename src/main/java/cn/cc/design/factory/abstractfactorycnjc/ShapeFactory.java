package cn.cc.design.factory.abstractfactorycnjc;

import cn.cc.design.factory.abstractfactorycnjc.impl.Circle;
import cn.cc.design.factory.abstractfactorycnjc.impl.Rectangle;
import cn.cc.design.factory.abstractfactorycnjc.impl.Square;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
