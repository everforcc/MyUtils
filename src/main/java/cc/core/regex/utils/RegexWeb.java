package cc.core.regex.utils;

/**
 * @author guokailong 2021-09-01
 */
public class RegexWeb {

    /**
     * 网络地址的正则
     */

    public static String bd_regex = "http://i0\\.hdslb\\.com/bfs/archive/\\w*\\.jpg";
    public static String bd_regex1 = "\\w*\\.jpg";
    public static String bd_regex2="\\w*";
    public static String bd_regex3 = "archive{1}";

    // String regex = "\\<" + tag + " id=\"" + id + "\"\\>(\\n){0,1}([u4e00-u9fa5])*.*(\\n){0,1}.*\\</" + tag + "\\>";
    // 回车 //n

    public static String bilibili_regexStart="\\<script\\>window\\.__INITIAL_STATE__=";
    public static String bilibili_middle = ".*";
    public static String bilibili_regexEnd=";\\(function\\(\\)\\{var s;\\(s=document\\.currentScript\\|\\|document\\.scripts\\[document\\.scripts\\.length-1\\]\\)\\.parentNode\\.removeChild\\(s\\);\\}\\(\\)\\);\\</script\\>";

}
