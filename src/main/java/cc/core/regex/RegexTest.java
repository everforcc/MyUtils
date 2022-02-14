package cc.core.regex;

import cc.core.regex.utils.RegexConstant;
import cc.core.regex.utils.RegexUtils;
import cc.core.regex.utils.RegexWeb;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author everforcc 2021-09-01
 */
public class RegexTest {

    public static void main(String[] args) {
        //bd_regex();
        //group();
        //bili_regex("");
        //fileName();
        //pattern_();
    }

    private static void fileName(){
//        Set<String> result = RegexUtils.matcheList(RegexConstant.fileName,"中文/");
//        System.out.println(result.size());
//        System.out.println(result.toString());
        //System.out.println(RegexUtils.matcheStr("^(1)\\d{10}$", "123456"));
        // ^((?!hede).)*$
        //String fileName = "^(?!.*[\\<*\\>*\\/*\\\\*\\|*\\:*\"*\\**\\?*\\；*\\ *])";
        String fileName = "^((?!(\\<*\\>*\\/*\\\\*\\|*\\:*\"*\\**\\?*\\；*\\ *)).)*$";
        System.out.println("a---" + RegexUtils.matcheStr(fileName, ":中文"));
        System.out.println("b---" + RegexUtils.matcheList(fileName, "中:文"));
        System.out.println("c---" + RegexUtils.matcheList(fileName, "中文:"));
        System.out.println("c---" + RegexUtils.matcheList(fileName, "中文"));
    }

    private static void pattern_(){

        //String fileName = "^((?!my string).)*$";
        //String s = RegexConstant.fileName;
        //String s = "\\<+|\\>+|\\/+|\\\\+|\\|+|\\:+|\"+|\\*+|\\?+|\\；+|\\ +|";
        String s = "\\<+|\\>+|\\/|\\\\+|\\|+|\\:+|\"+|\\*+|\\?+|\\；+|\\ +";
        String fileName = "^((?!(" + s + ")).)*$";
        System.out.println("c---" + RegexUtils.matcheList(fileName, "<中文mystring"));
        System.out.println("c---" + RegexUtils.matcheList(fileName, " my2string中文"));
        System.out.println("c---" + RegexUtils.matcheList(fileName, "my2string 中文"));
        System.out.println("c---" + RegexUtils.matcheList(fileName, "my2string中文 "));
        System.out.println("c---" + RegexUtils.matcheList(fileName, "中文mystring中文3"));
    }

    private static void wx_piccode(){
        List<String> result = RegexUtils.matcheList(RegexWeb.wx_piccode_regex,"中文!我啊#$&'()*+,/:;=?@-._~，abzABZ019");
        System.out.println(result.size());
        System.out.println(result.toString());
    }

    private static void bd_regex(){
        System.out.println(RegexUtils.isMatches(RegexWeb.bd_regex,"http://i0.hdslb.com/bfs/archive/74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg"));
        System.out.println(RegexUtils.isMatches(RegexWeb.bd_regex1,"74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg"));
        System.out.println(RegexUtils.isMatches(RegexWeb.bd_regex2,"74af0a6d77214ae7be8f2c11de0079d9004689c9"));
        System.out.println(RegexUtils.isMatches(RegexWeb.bd_regex3,"archive"));
    }

    private static void bili_regex(String html){
        String regex = RegexWeb.bilibili_regexStart + RegexWeb.bilibili_middle + RegexWeb.bilibili_regexEnd;
        String strTemp = RegexUtils.matcheStr(regex, html);
        // 如果不用组就要手动处理下面的代码
        //System.out.println(strTemp.replaceAll(regexStart,"").replaceAll(regexEnd,""));
        // 在 RegexWeb.bilibili_middle 两边加() 就可以用组(1)直接取到值

        String t_content = "2021-09-01";
        String t_regex = "\\d{4}-(\\d{2})-\\d{2}";
        System.out.println(RegexUtils.matcheStr(t_regex,t_content,0));
        System.out.println(RegexUtils.matcheStr(t_regex,t_content,1));
    }





}
