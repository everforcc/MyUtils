package cn.cc.core.regex.utils;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * @author c.c.
 * @date 2020/12/10
 */
public class RegexDate {

    // 常见的日期相关的正则

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
}
