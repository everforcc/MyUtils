package cn.cc.onjava8.strings;

// strings/JGrep.java
// A very simple version of the "grep" program
// {java JGrep
// WhitherStringBuilder.java 'return|for|String'}
import java.util.regex.*;
import java.nio.file.*;
import java.util.stream.*;

public class JGrep {
    public static void main(String[] args) throws Exception {
        /*if(args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }*/

        String arg[] = new String[2];
        arg[0] = "E:\\java\\onjava8\\string\\regex.txt";
        arg[1] = "^Ssct.+";
        Pattern p = Pattern.compile(arg[1]);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for(String line: Files.readAllLines(Paths.get(arg[0]))) {
            m.reset(line);
            while(m.find()) {
                System.out.println(index++ + ": " +
                        m.group() + ": " + m.start());
            }
        }
    }
}
/* Output:
0: for: 4
1: for: 4
*/
