package cn.cc.onjava8.streams;

// streams/RandInts.java
import java.util.*;
import java.util.stream.*;
public class RandInts {
    private static int[] rints = new Random(47).ints(0, 1000).limit(100).toArray();
    public static IntStream rands() {
        return Arrays.stream(rints);
    }
    /*
    上例将100个数值范围在 0 到 1000 之间的随机数流转换成为数组并将其存储在 rints 中。
    这样一来，每次调用 rands() 的时候可以重复获取相同的整数流。
    */

    static final int SZ = 14;
    public static void main(String[] args) {
        rands().limit(SZ)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        rands().limit(SZ)
                .parallel()
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        rands().limit(SZ)
                .parallel()
                .forEachOrdered(n -> System.out.format("%d ", n));
    }
}
