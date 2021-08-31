package cc.java8.onjava8.streams;

// onjava/Repeat.java
import static java.util.stream.IntStream.*;
public class Repeat {
    public static void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }
}

