package cn.cc.core.regex.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class RegexUtils {

    // 不是javabean 无所谓
    public static boolean isMatches(String regex,String string){
        Pattern pattern3 = Pattern.compile(regex);
        Matcher matcher3 = pattern3.matcher(string);
        //是否匹配到了
        if (matcher3.matches()) {
            return true;
        }
        return false;
    }

    public static String matcheStr(String regex,String string) {
        return matcheStr(regex,string,0);
    }
    public static String matcheStr(String regex,String string,int group){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            return matcher.group(group);
        }
        return null;
    }

}
