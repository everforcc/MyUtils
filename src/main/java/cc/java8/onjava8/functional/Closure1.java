package cc.java8.onjava8.functional;
// functional/Closure1.java

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Closure1 {
    int i;
    IntSupplier makeFun(int x) {
        return () -> x + i++;
    }
    public static void main(String[] args) {
        Closure1 c1 = new Closure1();
        // 如果你对同一个对象多次调用 makeFun() ，你最终会得到多个函数，它们共享 i 的存储空间：
        IntSupplier f1 = c1.makeFun(0);
        IntSupplier f2 = c1.makeFun(0);
        IntSupplier f3 = c1.makeFun(0);

        System.out.println(f1.getAsInt());
        System.out.println(f2.getAsInt());
        System.out.println(f3.getAsInt());
    }
}

class Closure2 {
    IntSupplier makeFun(int x) {
        int i = 0;
        // x 和 i 的操作都犯了同样的错误：被 Lambda 表达式引用的局部变量必须是 final 或者是等同 final 效果的。
        // return () -> x++ + i++;

        // 这就叫做等同 final 效果（Effectively Final）。这个术语是在 Java 8 才开始出现的，
        // 表示虽然没有明确地声明变量是 final 的，但是因变量值没被改变过而实际有了 final 同等的效果。
        // 如果局部变量的初始值永远不会改变，那么它实际上就是 final 的。
        /*i++;
        x++;*/
        return () -> x + i;

    }
}

class Closure6 {
    IntSupplier makeFun(int x) {
        int i = 0;
        i++;
        x++;
        // 但是这样i似乎没什么意义啊,x还行, 只是例子，而且这种也算一种方法吧
        final int iFinal = i;
        final int xFinal = x;
        // iFinal 和 xFinal 的值在赋值后并没有改变过，因此在这里使用 final 是多余的。
        return () -> xFinal + iFinal;
    }
}

/*class Closure7 {
    IntSupplier makeFun(int x) {
        Integer i = 0;
        i = i + 1;
        return () -> x + i;
    }
}*/

class Closure8 {
    Supplier<List<Integer>> makeFun() {
        final List<Integer> ai = new ArrayList<>();
        ai.add(1);
        System.out.println(ai.hashCode());
        return () -> ai;
    }
    public static void main(String[] args) {
        Closure8 c7 = new Closure8();
        List<Integer>
                l1 = c7.makeFun().get(),
                l2 = c7.makeFun().get();
        System.out.println(l1);
        System.out.println(l2);
        l1.add(42);
        l2.add(96);
        System.out.println(l1);
        System.out.println(l2);
    }
}

/**
 * 重新赋值引用会触发错误消息。如果只修改指向的对象则没问题，
 * 只要没有其他人获得对该对象的引用（这意味着你有多个实体可以修改对象，此时事情会变得非常混乱）
 * ，基本上就是安全的[^6]。
 */
/*class Closure9 {
    Supplier<List<Integer>> makeFun() {
        List<Integer> ai = new ArrayList<>();
        ai = new ArrayList<>(); // Reassignment
        return () -> ai;
    }
}*/

class AnonymousClosure {
    IntSupplier makeFun(int x) {
        int i = 0;
        // 同样规则的应用:
        // i++; // 非等同 final 效果
        // x++; // 同上
        return new IntSupplier() {
            public int getAsInt() { return x + i; }
        };
    }
}
