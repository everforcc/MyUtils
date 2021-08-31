package cc.design.factory.abstractfactorycnjc;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public abstract class AbstractFactory {

    //  两个抽象的对象
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape) ;

}
