package cc.java8.onjava8.functional;

// functional/RecursiveFactorial.java

public class RecursiveFactorial {
    static IntCall fact;
    public static void main(String[] args) {

        // n == 0 ? 1 : n * (n - 1)
        // 停止条件 n = 0
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);

        //
        for(int i = 0; i <= 10; i++) {
            System.out.println(fact.call(i));
        }
    }
}
