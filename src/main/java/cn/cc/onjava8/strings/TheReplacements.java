package cn.cc.onjava8.strings;

// strings/TheReplacements.java
import java.util.regex.*;
import java.nio.file.*;
import java.util.stream.*;

/*! Here's a block of text to use as input to
    the regular expression matcher. Note that we
    first extract the block of text by looking for
    the special delimiters, then process the
    extracted block. !*/

public class TheReplacements {
    public static void main(String[] args) throws Exception {

        //String s = Files.lines(Paths.get("TheReplacements.java")).collect(Collectors.joining("\n"));

        String s = "Here's a block of text to use as input to\n" +
                "    the regular expression matcher. Note that we\n" +
                "    first extract the block of text by looking for\n" +
                "    the special delimiters, then process the\n" +
                "    extracted block.";

        // Match specially commented block of text above:
        Matcher mInput = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(s);

        if(mInput.find()) {
            s = mInput.group(1); // Captured by parentheses
        }

        // Replace two or more spaces with a single space:
        s = s.replaceAll(" {2,}", " ");

        // Replace 1+ spaces at the beginning of each
        // line with no spaces. Must enable MULTILINE mode:
        s = s.replaceAll("(?m)^ +", ""); // 剔除掉每一行的空格

        System.out.println(s);
        System.out.println(" >>> ");

        s = s.replaceFirst("[aeiou]", "(VOWEL1)");

        StringBuffer sbuf = new StringBuffer();
        Pattern p = Pattern.compile("[aeiouc]");
        Matcher m = p.matcher(s);
        // Process the find information as you
        // perform the replacements:
        while(m.find()) {
            // 匹配到的转换为大写
            m.appendReplacement(sbuf, m.group().toUpperCase());
        }
        // Put in the remainder of the text:
        System.out.println(sbuf);
        System.out.println(" >>> ");
        m.appendTail(sbuf);
        System.out.println(sbuf);
    }
}
/* Output:
Here's a block of text to use as input to
the regular expression matcher. Note that we
first extract the block of text by looking for
the special delimiters, then process the
extracted block.
H(VOWEL1)rE's A blOck Of tExt tO UsE As InpUt tO
thE rEgUlAr ExprEssIOn mAtchEr. NOtE thAt wE
fIrst ExtrAct thE blOck Of tExt by lOOkIng fOr
thE spEcIAl dElImItErs, thEn prOcEss thE
ExtrActEd blOck.
*/
