package cc.java8.onjava8.functional;

// functional/TransformFunction.java

import java.util.function.*;

class I {
    @Override
    public String toString() { return "I"; }
}

class O {
    @Override
    public String toString() { return "O"; }
}

public class TransformFunction {
    // 2.从返回的方法里面来执行下一步 in 实际上就是一个方法，在执行完in方法之后，在调用下一个方法 andThen(  );
    static Function<I,O> transform(Function<I,O> in) {
        return in.andThen(o -> {
            System.out.println(o);
            return o;
        });
    }
    // 1.这里为实际的第一步，在传参的时候，执行一个方法，将方法的结果返回
    static Function<I,O> f2 = transform(i -> {
        System.out.println(i);
        return new O();
    });

    public static void main(String[] args) {
        /*Function<I,O> f2 = transform(i -> {
            System.out.println(i);
            return new O();
        });*/
        // 0.前面的都是声明的过程, 实际执行在下面
        System.out.println("--- ---");
        O o = f2.apply(new I());
    }
}
