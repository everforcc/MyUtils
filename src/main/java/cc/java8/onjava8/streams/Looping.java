package cc.java8.onjava8.streams;

// streams/Looping.java

import static cc.java8.onjava8.streams.Repeat.repeat;

public class Looping {
    static void hi() {
        System.out.println("Hi!");
    }
    public static void main(String[] args) {
        repeat(3, () -> System.out.println("Looping!"));
        repeat(2, Looping::hi);
    }
}

