package cn.cc.onjava8.functional;

// functional/Strategize.java

interface Strategy {
    // 提供单一接口来完成函数式功能
    String approach(String msg);
}

class Soft implements Strategy {
    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}

// 类
class Unrelated {
    static String twice(String msg) {
        return msg + " " + msg;
    }
}

public class Strategize {
    Strategy strategy;
    String msg;
    Strategize(String msg) {
        // 默认策略
        strategy = new Soft(); // [1]
        this.msg = msg;
    }

    void communicate() {
        System.out.println("test:"+strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] strategies = {
                // 匿名内部类
                new Strategy() { // [2]
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                // 传递方法，截取字符串
                msg -> msg.substring(0, 5), // [3]
                // 方法引用
                Unrelated::twice, // [4]
        };
        Strategize s = new Strategize("Hello there");
        s.communicate();

        for(Strategy newStrategy : strategies) {
            // 传入不同的策略
            s.changeStrategy(newStrategy); // [5]
            // 每次调用都会产生不同的行为
            s.communicate(); // [6]
        }

    }
}
