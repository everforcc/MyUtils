package cn.cc.onjava8.streams;

// streams/OptionalsFromEmptyStreams.java
import java.util.*;
import java.util.stream.*;
class OptionalsFromEmptyStreams {
    public static void main(String[] args) {
        System.out.println(Stream.<String>empty()
                .findFirst());
        System.out.println(Stream.<String>empty()
                .findAny());
        System.out.println(Stream.<String>empty()
                .max(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty()
                .min(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty()
                .reduce((s1, s2) -> s1 + s2));
        System.out.println(IntStream.empty()
                .average());
    }
}

class OptionalBasics {
    static void test(Optional<String> optString) {
        if(optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside!");
    }
    public static void main(String[] args) {
        test(Stream.of("Epithets").findFirst());
        test(Stream.<String>empty().findFirst());
    }
}
