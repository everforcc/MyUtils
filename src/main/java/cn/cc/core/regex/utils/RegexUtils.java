package cn.cc.core.regex.utils;

import java.util.HashSet;
import java.util.Set;
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

    public static String matcheStr(String regex,String content) {
        return matcheStr(regex,content,0);
    }
    public static String matcheStr(String regex,String content,int group){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            return matcher.group(group);
        }
        return null;
    }

    public static Set<String> matcheList(String regex,String content) {
        return matcheList(regex,content,0);
    }
    public static Set<String> matcheList(String regex,String content,int group){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        //是否匹配到了
        Set<String> stringSet = new HashSet<>();
        while (matcher.find()) {// 匹配出所有符合的
            stringSet.add(matcher.group(group));
            //System.out.println(matcher.group(0));
        }
        return stringSet;
    }

    // String regex = "\\<" + tag + " id=\"" + id + "\"\\>(\\n){0,1}([u4e00-u9fa5])*.*(\\n){0,1}.*\\</" + tag + "\\>";
    // 回车 //n
}
