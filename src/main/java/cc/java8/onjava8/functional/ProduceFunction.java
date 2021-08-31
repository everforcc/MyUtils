package cc.java8.onjava8.functional;

// functional/ProduceFunction.java

import java.util.function.*;

// 使用继承，可以轻松地为专用接口创建别名。
interface FuncSS extends Function<String, String> {} // [1]

public class ProduceFunction {
    static FuncSS produce() {
        //  使用 Lambda 表达式，可以轻松地在方法中创建和返回一个函数。
        return s -> s.toLowerCase(); // [2]
    }
    public static void main(String[] args) {
        FuncSS f = produce();

        System.out.println(f.apply("YELLING"));
    }
}

