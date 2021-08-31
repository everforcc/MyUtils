package cc.java8.onjava8.strings;

// strings/Finding.java
import java.util.regex.*;

public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+")
                .matcher(
                        "Evening is full of the linnet's wings");
        while(m.find())
            System.out.print(m.group() + " >> ");
        System.out.println("\r\n");
        int i = 30;
        while(m.find(i)) {
            System.out.println(m.group() + " >> " + i + " >> ");
            i++;
        }
    }
}
/* Output:
Evening is full of the linnet s wings
Evening vening ening ning ing ng g is is s full full
ull ll l of of f the the he e linnet linnet innet nnet
net et t s s wings wings ings ngs gs s
*/
