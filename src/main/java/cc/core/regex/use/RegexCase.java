package cc.core.regex.use;

import cc.core.regex.utils.RegexUtils;

public class RegexCase {

    public static void main(String[] args) {

    }

    private static String getRegex1="(\\w)\\1{2}(?!\\1)(\\w)\\2{2}";

    // 匹配模式
    public static void bookName(String name){
        System.out.println(RegexUtils.matcheStr("《(.*?)》(abc)", name));
    }

    public static void epRegex(String name){
        String regexStart="\\<script\\>window\\.__INITIAL_STATE__=";
        String regexEnd=";\\(function\\(\\)\\{var s;\\(s=document\\.currentScript\\|\\|document\\.scripts\\[document\\.scripts\\.length-1\\]\\)\\.parentNode\\.removeChild\\(s\\);\\}\\(\\)\\);\\</script\\>";
        // String regex = "\\<script\\>window\\.__INITIAL_STATE__=.*;\\(function\\(\\)\\{var s;\\(s=document\\.currentScript\\|\\|document\\.scripts\\[document\\.scripts\\.length-1\\]\\)\\.parentNode\\.removeChild\\(s\\);\\}\\(\\)\\);\\</script\\>" ;
        String regex = regexStart +".*" + regexEnd ;
        String strTemp = RegexUtils.matcheStr(regex, name);
        System.out.println(strTemp.replaceAll(regexStart,"").replaceAll(regexEnd,""));
    }

    public static void main1(String[] args) {

        try {
            //bookName("《wo》abc");
            //String html = Jsoup.parse(new File(ConstantFile.javaFilePath + "/bilibili/html/ep29919.txt"), ConstantCharSet.UTF_8).toString();
            // System.out.println(html);
            //epRegex(html);
            //ttregex();
            //regexTest();

            String html = "<ul id=\"contentdp\">\n" +
                    "  本文来自 轻小说文库(http://www.wenku8.com)\n" +
                    " </ul>";
            String tag = "ul";
            String id = "contentdp";
            String regex = "\\<" + tag + " id=\"" + id + "\"\\>(\\n){0,1}([u4e00-u9fa5])*.*(\\n){0,1}.*\\</" + tag + "\\>";

            String tagHtml = "<ul id=\"contentdp\">\n";
            String tagRegex = "\\<" + tag + " id=\"" + id + "\"\\>(\\n){0,1}";
            System.out.println("tagRegex:" + tagRegex);
            System.out.println("tagHtml:" + tagHtml);

            System.out.println("match:" + RegexUtils.matcheStr(tagRegex, tagHtml));

            String chinese = "本文来自 轻小说文库(http://www.wenku8.com)\n";
            String regex2 = "([u4e00-u9fa5])*.*(\\n){0,1}";
            System.out.println("regex2:" + regex2);
            System.out.println("chinese:" + chinese);

            System.out.println("match:" + RegexUtils.matcheStr(regex2, chinese));

            String tagEndHtml = " </ul>";;
            String tagRegexEnd = ".*\\</" + tag + "\\>";
            //String tagRegexEnd = ".*\\</ul\\>";

            System.out.println("match:" + RegexUtils.matcheStr(tagRegexEnd, tagEndHtml));

            System.out.println("match:" + RegexUtils.matcheStr(regex, html));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
