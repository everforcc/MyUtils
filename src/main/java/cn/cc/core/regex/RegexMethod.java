package cn.cc.core.regex;

import java.text.SimpleDateFormat;
import java.util.UUID;
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

    public String yyyyMMdd(String voucherdate)throws Exception{

        String regex0="\\d{8}";
        String regex1="\\d{4}-\\d{2}-\\d{2}";
        String regex2="\\d{4}年\\d{2}月\\d{2}日";
        String regex3="\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
        String regex4="\\d{4}\\\\\\d{2}\\\\\\d{2}";
        String regex5="\\d{4}\\\\\\d{2}\\\\\\d{2} \\d{2}:\\d{2}";

        if(Pattern.compile(regex0).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(voucherdate));
            return string;
        }
        if(Pattern.compile(regex1).matcher(voucherdate).matches()){
            return voucherdate;
        }
        if(Pattern.compile(regex2).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy年MM月dd日").parse(voucherdate));
            return string;
        }
        if(Pattern.compile(regex3).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(voucherdate));
            return string;
        }
        if(Pattern.compile(regex4).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(voucherdate));
            return string;
        }
        if(Pattern.compile(regex5).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(voucherdate));
            return string;
        }

        return "0000-00-00";
    }

    public String bookName(String name){

        //String c="《wo》 》";
        Pattern pattern = Pattern.compile("《{1}(.*?)》{1}");
        Matcher matcher = pattern.matcher(name);
        //是否匹配到了
        if (matcher.find()) {// 进入后可以全匹配
            System.out.println(matcher.group(0)); // 《wo》
            System.out.println(matcher.group(1)); // wo
        }else {

        }
        return UUID.randomUUID().toString();

    }



}
