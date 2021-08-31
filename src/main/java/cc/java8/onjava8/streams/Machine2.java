package cc.java8.onjava8.streams;

// streams/Machine2.java
import cc.java8.onjava8.onjava.Operations;

import java.util.*;
public class Machine2 {
    public static void main(String[] args) {
        Arrays.stream(new Operations[] {
                () -> Operations.show("Bing"),
                () -> Operations.show("Crack"),
                () -> Operations.show("Twist"),
                () -> Operations.show("Pop")
        }).forEach(Operations::execute);
    }
}

