package cn.cc.core.regex.use;

import cn.cc.core.regex.utils.RegexUtils;

public class RegexCase {

    String getRegex1="(\\w)\\1{2}(?!\\1)(\\w)\\2{2}";

    public static void regexTest(){
        String a="D:\\3.存档\\1.视频";
        String name="a.txt";
        System.out.println(a);
        System.out.println(a.replace("D:",""));
        System.out.println(a.replaceAll("\\\\","/"));
        System.out.println(a.replaceAll("\\\\","/").replace("D:",""));
        System.out.println(a.substring(a.lastIndexOf(".")+1,a.length()));
        String str="abc_cba";
        System.out.println(str.substring(0,str.indexOf("_")));
        String sss="ABC.DEF";
        System.out.println(sss.toLowerCase());
    }

    private static final String regex = "http://i0\\.hdslb\\.com/bfs/archive/\\w*\\.jpg";
    private static final String regex1 = "\\w*\\.jpg";
    private static final String regex2="\\w*";
    private static final String regex3 = "archive{1}";
    public static void ttregex(){
        System.out.println(RegexUtils.isMatches(regex,"http://i0.hdslb.com/bfs/archive/74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg"));
        System.out.println(RegexUtils.isMatches(regex1,"74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg"));
        System.out.println(RegexUtils.isMatches(regex2,"74af0a6d77214ae7be8f2c11de0079d9004689c9"));
        System.out.println(RegexUtils.isMatches(regex3,"archive"));

    }

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

    public static void main(String[] args) {

        try {
            //bookName("《wo》abc");
            //String html = Jsoup.parse(new File("E:\\bilibili\\html\\ep29919.txt"), ConstantCharSet.UTF_8).toString();
            // System.out.println(html);
            //epRegex(html);
            //ttregex();
            regexTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
