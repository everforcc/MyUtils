package cc.design.design2factory.abstractfactorycnjc;

import cc.design.design2factory.abstractfactorycnjc.impl.Circle;
import cc.design.design2factory.abstractfactorycnjc.impl.Rectangle;
import cc.design.design2factory.abstractfactorycnjc.impl.Square;

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
