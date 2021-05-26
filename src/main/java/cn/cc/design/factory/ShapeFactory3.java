package cn.cc.design.factory;

// patterns/ShapeFactory3.java
// Polymorphic factory methods
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

interface PolymorphicFactory {
    Shape create();
}

class RandomShapes implements Supplier<Shape> {

    private final PolymorphicFactory[] factories;
    private Random rand = new Random(42);

    RandomShapes(PolymorphicFactory... factories){
        this.factories = factories;
    }

    public Shape get() {
        return factories[ rand.nextInt(factories.length)].create();
    }

}

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class ShapeFactory3 {

    public static void main(String[] args) {

        RandomShapes rs = new RandomShapes(
                Circle::new,
                Square::new,
                Triangle::new);

        Stream.generate(rs)
                .limit(6)
                .peek(Shape::draw)
                .peek(Shape::erase)
                .count();

    }

}