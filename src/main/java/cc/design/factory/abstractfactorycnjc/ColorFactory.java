package cc.design.factory.abstractfactorycnjc;

import cc.design.factory.abstractfactorycnjc.impl.Blue;
import cc.design.factory.abstractfactorycnjc.impl.Green;
import cc.design.factory.abstractfactorycnjc.impl.Red;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
