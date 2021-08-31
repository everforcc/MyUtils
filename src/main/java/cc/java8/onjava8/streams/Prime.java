package cc.java8.onjava8.streams;

// streams/Prime.java
import java.util.stream.*;
import static java.util.stream.LongStream.*;
public class Prime {
    public static Boolean isPrime(long n) {
        // rangeClosed() 包含了上限值。如果不能整除，即余数不等于 0，则 noneMatch() 操作返回 true，
        // 如果出现任何等于 0 的结果则返回 false。 noneMatch() 操作一旦有失败就会退出。
        return rangeClosed(2, (long)Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
    }
    public LongStream numbers() {
        return iterate(2, i -> i + 1)
                .filter(Prime::isPrime);
    }
    public static void main(String[] args) {
        new Prime().numbers()
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        new Prime().numbers()
                .skip(90)
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
    }
}

