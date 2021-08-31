package cc.java8.onjava8.functional;

// functional/ConsumeFunction.java

import java.util.function.*;

class One {}
class Two {}

//
public class ConsumeFunction {
   // 要消费一个函数，消费函数需要在参数列表正确地描述函数类型。代码示例
    static Two consume(Function<One,Two> onetwo) {
        return onetwo.apply(new One());
    }
    public static void main(String[] args) {
        // 没看懂传参设呢么意思
        // 是因为Function的aply函数，只取了第一个参数
        Two two = consume(one -> new Two());
    }
}
