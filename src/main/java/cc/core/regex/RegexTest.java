package cc.core.regex;

import cc.core.regex.utils.RegexUtils;
import cc.core.regex.utils.RegexWeb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guokailong 2021-09-01
 */
public class RegexTest {

    public static void main(String[] args) {
        //bd_regex();
        //group();
        bili_regex("");
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

    /**
     * 正则表达式普通组
     * 0全部
     * 从(开始算，第一个包裹的组就是1
     */
    private static void group(){
        String content = "2021-09-01";
        String regex = "(\\d{4})-((\\d{2})-(\\d{2}))";
        System.out.println(RegexUtils.matcheStr(regex,content,0));
        System.out.println(RegexUtils.matcheStr(regex,content,1));
        System.out.println(RegexUtils.matcheStr(regex,content,2));
        System.out.println(RegexUtils.matcheStr(regex,content,3));
        System.out.println(RegexUtils.matcheStr(regex,content,4));

        String regex_name = "(?<year>\\d{4})-(?<md>(?<month>\\d{2})-(?<date>\\d{2}))";
        System.out.println(RegexUtils.matcheStr(regex_name,content,0));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"year"));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"md"));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"month"));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"date"));

    }



}
