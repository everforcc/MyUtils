package cn.cc.design.factory;

import java.util.stream.Stream;

/**
 * @author c.c.
 * @date 2021/3/24
 */
public class FactoryTest {

    public static void test(FactoryMethod factory) {
        Stream.of("Circle", "Square", "Triangle",
                "Square", "Circle", "Circle", "Triangle")
                .map(factory::create)
                .peek(Shape::draw)
                .peek(Shape::erase)
                .count(); // Terminal operation
        // 要记住除非你在最后使用了一个终结操作，否则Stream不会做任何事情。在这里，count()的值被丢弃了。
    }

}
