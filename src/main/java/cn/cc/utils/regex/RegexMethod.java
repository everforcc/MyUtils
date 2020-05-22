package cn.cc.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMethod {
     String getRegex1="(\\w)\\1{2}(?!\\1)(\\w)\\2{2}";
    public void regexTest(){
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

    String regex2="\\w*";

    private static final String regex3 = "article{1}";

    public void ttregex(){
        //正则匹配url
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("http://i0.hdslb.com/bfs/archive/74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg");
        //是否匹配到了
        if (matcher.matches()) {
            System.out.println("true");
        }

        //正则匹配url1
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher("74af0a6d77214ae7be8f2c11de0079d9004689c9.jpg");
        //是否匹配到了
        if (matcher1.matches()) {
            System.out.println("true1");
        }

        //正则匹配url2
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher("74af0a6d77214ae7be8f2c11de0079d9004689c9");
        //是否匹配到了
        if (matcher2.matches()) {
            System.out.println("true2");
        }
        ////i0.hdslb.com
        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher3 = pattern3.matcher("archive");
        //是否匹配到了
        if (matcher3.matches()) {
            System.out.println("true3");
        }

    }

}
