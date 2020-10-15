package cn.cc.onjava8.streams;

// streams/Fibonacci.java
import java.util.stream.*;
public class Fibonacci {
    int x = 1;

    Stream<Integer> numbers() {
        return Stream.iterate(0, i -> {
            System.out.println("x="+x+",i="+i);
            int result = x + i;
            x = i;
            return result;
        });
    }

    public static void main(String[] args) {
        new Fibonacci().numbers()
        // 使用了一个之前没有见过的 skip() 操作。它根据参数丢弃指定数量的流元素。在这里，我们丢弃了前 20 个元素。
                //.skip(2) // 过滤前 20 个
                .limit(5) // 然后取 10 个
                .forEach(System.out::println);
    }
}

