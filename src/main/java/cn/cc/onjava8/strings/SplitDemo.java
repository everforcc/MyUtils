package cn.cc.onjava8.strings;

// strings/SplitDemo.java
import java.util.regex.*;
import java.util.*;

public class SplitDemo {
    public static void main(String[] args) {
        String input =
                "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(
                Pattern.compile("!!").split(input)));
        // Only do the first three:
        System.out.println(Arrays.toString(
                Pattern.compile("!!").split(input, 3))); // 最多分成三个
    }
}
/* Output:
[This, unusual use, of exclamation, points]
[This, unusual use, of exclamation!!points]
*/

