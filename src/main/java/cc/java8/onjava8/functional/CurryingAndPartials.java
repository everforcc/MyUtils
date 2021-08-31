package cc.java8.onjava8.functional;

// functional/CurryingAndPartials.java

import java.util.function.*;

public class CurryingAndPartials {
    // 未柯里化:
    static String uncurried(String a, String b) {
        return a + b;
    }
    public static void main(String[] args) {
        // 柯里化的函数:
        Function<String, Function<String, String>> sum =
                a -> b -> a + b; // [1]

        System.out.println(uncurried("Hi ", "Ho"));

        Function<String, String>
                hi = sum.apply("Hi "); // [2]
        System.out.println(hi.apply("Ho"));

        // 部分应用:
        Function<String, String> sumHi =
                sum.apply("Hup ");
        System.out.println(sumHi.apply("Ho"));
        System.out.println(sumHi.apply("Hey"));
    }
}

class Curry3Args {
    public static void main(String[] args) {
        // 从上到下 依次是 abc
        Function<String,
                Function<String,
                        Function<String, String>>> sum =
                a -> b -> c -> a + b + c;
        // 对于每个级别的箭头级联（Arrow-cascading），你都要在类型声明中包裹另一层 Function。
        Function<String,
                Function<String, String>> hi =
                sum.apply("Hi ");
        Function<String, String> ho =
                hi.apply("Ho ");
        System.out.println(ho.apply("Hup"));
    }
}

// 处理基本类型和装箱时，请使用适当的函数式接口：
class CurriedIntAdd {
    public static void main(String[] args) {
        IntFunction<IntUnaryOperator>
                curriedIntAdd = a -> b -> a + b;
        IntUnaryOperator add4 = curriedIntAdd.apply(4);
        System.out.println(add4.applyAsInt(5));
    }
}
