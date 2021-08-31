package cc.core.string.utils;

import java.util.Formatter;

/**
 * @author 郭凯龙
 * @data 2021/8/26 0026
 */
public class FormatterUtils {

    void t7(){
        Formatter f = new Formatter();
        f.format("%-20s","1");
        System.out.println("|" + f.toString() + "|");

        Formatter f1 = new Formatter();
        f1.format("%-20.20s","中文"); // 0123456789
        System.out.println("|" + f1.toString() + "|");
    }

}
