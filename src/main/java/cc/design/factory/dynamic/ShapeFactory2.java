package cc.design.factory.dynamic;

import cc.design.factory.BadShapeCreation;
import cc.design.factory.FactoryMethod;
import cc.design.factory.FactoryTest;
import cc.design.factory.Shape;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author c.c.
 * @date 2021/3/24
 * <font face="SimSun" size=5 color="red">使用反射在首次需要时将Shape的构造器动态加载到工厂列表中</font>
 */
public class ShapeFactory2 implements FactoryMethod {

    /**
     *  <font face="SimSun" size=5 color="red">使用反射在首次需要时将Shape的构造器动态加载到工厂列表中</font>
     */

    Map<String, Constructor> factories = new HashMap<>();
    static Constructor load(String id) {
        System.out.println("loading " + id);
        try {
            return Class.forName("cn.cc.design.factory." + id)
                    .getConstructor();
        } catch(ClassNotFoundException |
                NoSuchMethodException e) {
            throw new BadShapeCreation(id);
        }
    }

    @Override
    public Shape create(String id) {
        try {
            return (Shape)factories
                    .computeIfAbsent(id, ShapeFactory2::load)
                    .newInstance();
        } catch(InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {
            throw new BadShapeCreation(id);
        }
    }

    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory2());
    }

}
