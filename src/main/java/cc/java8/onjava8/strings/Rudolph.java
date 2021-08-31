package cc.java8.onjava8.strings;

// strings/Rudolph.java

public class Rudolph {
    public static void main(String[] args) {
        for(String pattern : new String[]{
                "Rudolph",
                "[rR]udolph",
                "[rR][aeiou][a-z]ol.*",
                "R.*" })
            System.out.println("Rudolph".matches(pattern));

        System.out.println("abc".matches("[abcd]+"));
    }
}
/* Output:
true
true
true
true
*/
