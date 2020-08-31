package cn.cc.core.date.utils;

import cn.cc.core.date.constant.Constant_Date;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Yukino
 * 2020/5/12
 */
public class DateUtils {

    /**
     * 跟定的需要时间类型的正则表达式，默认 yyyy-MM-dd
     * yyyy-MM-dd hh:mm
     * yyyyMMddHHmmss
     */
    public static String pattern= Constant_Date.mode_1;

    /**
     * 需要做一个 yyyy-MM-dd 和正则表达式对应的组合 这样的话添加起来比较方便 比如
     * [0-9]{8} 和 yyyyMMdd
     */

    /**
     * 通用格式化日期
     * @param voucherdate
     * @return
     * @throws Exception
     */
    public static String formatDate(String voucherdate)throws Exception{
        String regex="[0-9]{8}";
        String regex1="[0-9]{4}-[0-9]{2}-[0-9]{2}";
        String regex2="[0-9]{4}年[0-9]{2}月[0-9]{2}日";
        String regex3="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";
        if(Pattern.compile(regex).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat(pattern).format(new SimpleDateFormat("yyyyMMdd").parse(voucherdate));
            return string;
        }
        if(Pattern.compile(regex1).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat(pattern).format(new SimpleDateFormat("yyyy-MM-dd").parse(voucherdate));
            return voucherdate;
        }
        if(Pattern.compile(regex2).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat(pattern).format(new SimpleDateFormat("yyyy年MM月dd日").parse(voucherdate));
            return string;
        }
        if(Pattern.compile(regex3).matcher(voucherdate).matches()){
            String string = new SimpleDateFormat(pattern).format(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(voucherdate));
            return string;
        }
        return "0000-00-00";
    }

    /**
     * 根据字符串格式 做出类似上面的格式
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date strParseToDate(String str) throws ParseException {

        return new SimpleDateFormat("yyyyMMdd").parse(str);
    }

    /**
     * 计算两个时间的时差，返回 秒 s
     * @param startDate
     * @param endDate
     * @return
     */
    public static double timedif(Date startDate,Date endDate){
        //得到的是时间戳的差
        return  (endDate.getTime()-startDate.getTime())/1000;
    }

    /**
     * 根据正则返回当前时间
     * @param regex 根据给定的正则 yyyy-MM-dd 这种
     * @return
     */
    public static String nowTimeRegex(String regex){
        return new SimpleDateFormat(regex).format(new Date(System.currentTimeMillis()));
    }

    /**
     * 默认返回当前时间 yyyy-MM-dd
     * @return
     */
    public static String now(){
        return new SimpleDateFormat(Constant_Date.mode_1).format(new Date(System.currentTimeMillis()));
    }





}
