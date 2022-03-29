package cc.core.regex.utils;

import java.util.List;
import java.util.Set;

public class RegexModel {



    /**
     * 记录正则中常见的模式
     */

    public static void main(String[] args) {
        //greed();
        //groupStr();
        //strReplaceReg();
        //group();
        modelMatch();
        //matchHTML();
        //matchAttribute();
    }

    /**
     * 1. 贪婪，非贪婪模式
     */
    private static String greedReg = "<div>(.*?)</div>";
    private static String gerrdContent = "<div>a</div><div>b</div>";
    private static void greed(){
        List<String> stringSet = RegexUtils.matcheList(greedReg,gerrdContent);
        System.out.println(stringSet.size());
        stringSet.forEach(System.out::println);
    }

    /**
     * 2. 或
     */
    private static String a = "http abc";
    private static String b = "ftp abc";
    private static String c = "svn abc";
    private static String abcReg = "(http|ftp|svn) abc";
    private static void orStrs(){
        System.out.println(RegexUtils.matcheStr(abcReg, a));
        System.out.println(RegexUtils.matcheStr(abcReg, b));
        System.out.println(RegexUtils.matcheStr(abcReg, c));
    }

    /**
     * 3. 数据分组和取数据
     * 3.1 引用分组1的内容
     */
    private static String num = "11aaa333ccc";
    private static String numGroup = "(\\d)\\1\\1";
    private static void groupStr(){
        List<String> stringSet = RegexUtils.matcheList(numGroup,num);
        System.out.println(stringSet.size());
        stringSet.forEach(System.out::println);
    }

    /**
     * 3.2 替换中调用分组的内容
     */
    private static String params  ="abc=aaa";
    private static String startReg = "abc=([^$]+)";
    //private static String startReg = "abc=(a+)";
    private static String endReg = "username=$1";

    private static void strReplaceReg(){
        System.out.println(RegexUtils.matcheStr(startReg,params));
        // 字符串
        System.out.println(params.replace(startReg, endReg));
        // 正则
        System.out.println(params.replaceAll(startReg, endReg));
        System.out.println(params.replaceFirst(startReg, endReg));

    }

    /**
     * 组数据
     */
    /**
     * 正则表达式普通组
     * 0全部
     * 从(开始算，第一个包裹的组就是1
     */
    private static void group(){
        /**
         * 按照数字顺序取
         */
        String content = "2021-09-01";
        String regex = "(\\d{4})-((\\d{2})-(\\d{2}))";
        System.out.println("按照顺序捕获");
        System.out.println(RegexUtils.matcheStr(regex,content,0));
        System.out.println(RegexUtils.matcheStr(regex,content,1));
        System.out.println(RegexUtils.matcheStr(regex,content,2));
        System.out.println(RegexUtils.matcheStr(regex,content,3));
        System.out.println(RegexUtils.matcheStr(regex,content,4));

        /**
         * 按照数字顺序取
         */
        String noGroupRegex = "(?:\\d{4})-(?:(?:\\d{2})-(\\d{2}))";
        System.out.println("去除非捕获组捕获");
        System.out.println(RegexUtils.matcheStr(noGroupRegex,content,0));
        System.out.println(RegexUtils.matcheStr(noGroupRegex,content,1));

        /**
         * 自定义名称取
         */
        String regex_name = "(?<year>\\d{4})-(?<md>(?<month>\\d{2})-(?<date>\\d{2}))";
        System.out.println("自定义组名捕获");
        System.out.println(RegexUtils.matcheStr(regex_name,content,0));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"year"));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"md"));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"month"));
        System.out.println(RegexUtils.matcheStr(regex_name,content,"date"));
    }

    /**
     * 4. 模式匹配
     * 4.1 ?i 忽略大小写
     */
    private static String upStri = "abc\r\nAbc";
    private static String lowStri = "abc\r\nAbc";
    private static String regexStri = "((?i)ABC)";

    private static String upStrs =  "ab\n" +
            "cAbc";
    private static String lowStrs = "abc\n" +
            "cAbc";
    /**
     * 单行多行模式没搞懂
     */
    private static String regexStrs = "((?is)c?abc*.?)";
    private static String regexStrm = "((?im)c?abc*.?)";
    //private static String regexStrm = "^((?im)c*abc*)$";
    //private static String regexStrm = "^(.*)$";

    public static void modelMatch(){
        System.out.println("---------------");
        System.out.println(RegexUtils.matcheList(regexStri, upStri));
        System.out.println(RegexUtils.matcheList(regexStri, lowStri));
        System.out.println("---------------");
        System.out.println(RegexUtils.matcheList(regexStrs, upStrs));
        System.out.println(RegexUtils.matcheList(regexStrs, lowStrs));
        System.out.println("---------------");
        System.out.println(RegexUtils.matcheList(regexStrm, upStrs));
        System.out.println(RegexUtils.matcheList(regexStrm, lowStrs));
//        System.out.println(RegexUtils.matcheList(regexStri, upStrs));
//        System.out.println(RegexUtils.matcheList(regexStri, lowStrs));
    }

    /**
     * 5. 锚点
     * 定位数据
     * 然后用子模式
     */
    private static String divHTML = "<div id=\"module_1\">\n" +
            "    <div class=\"content\">\n" +
            "              content 1\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div id=\"module_2\">\n" +
            "    <div class=\"content\">\n" +
            "              content 2\n" +
            "    </div>\n" +
            "</div>";

    private static String htmlDivReg = "<div id=\"module_1\">([\\s\\S]*?)</div>\\s*<div id=\"module_2\">";
    private static String htmlDivContentReg = "<div class=\"content\">([\\s\\S]*?)</div>";
    private static void matchHTML(){
        List<String> divMatchHTML = RegexUtils.matcheList(htmlDivReg,divHTML);
        System.out.println(divMatchHTML.get(0));
        System.out.println("---------------");
        List<String> divMatchContent = RegexUtils.matcheList(htmlDivContentReg,divMatchHTML.get(0));
        System.out.println(divMatchContent);

        System.out.println("---------------");
        System.out.println("start[" + RegexUtils.matcheStr(htmlDivContentReg, divMatchHTML.get(0), 1) + "]end");
    }

    private static String attributeHTML = "<meta content=\"text/html; charset=utf-8\" http-equiv=\"content-type\">";
    private static String attributeReg = "<meta[^>]*?charset=([^\"]+)\"";

    private static void matchAttribute(){
        System.out.println(RegexUtils.matcheStr(attributeReg, attributeHTML, 1));
    }

    /**
     * 6. 环视
     */
    private static void assetReg(){

    }

}
