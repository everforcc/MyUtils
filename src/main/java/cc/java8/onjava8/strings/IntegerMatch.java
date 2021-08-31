package cc.java8.onjava8.strings;

// strings/IntegerMatch.java

public class IntegerMatch {
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));
        System.out.println("\\".matches("\\\\"));
    }
}
/* Output:
true
true
false
true
*/
