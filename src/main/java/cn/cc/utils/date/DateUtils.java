package cn.cc.utils.date;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
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
    public static String pattern="yyyy-MM-dd";


    SimpleDateFormat defaultSDF = new SimpleDateFormat("yyyy-MM-dd");

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
     * 时间戳转化为时间
     *
     * @param beginDate
     * @return
     */
    public static String timestampToDateStr(Long beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(beginDate)); // 时间戳转换日期
        System.out.println(sd);
        return sd;
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timestampToDateStrFormat(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    /**
     *
     * @param
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */

    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
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
     * @param regex
     * @return
     */
    public static String nowTimeRegex(String regex){
        return new SimpleDateFormat(regex).format(new Date(System.currentTimeMillis()));
    }

    /**
     * 日历 没用过
     */
    @Test
    public void calendarDate(){
        Date date = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.SECOND, 2);
        date = cd.getTime();
        System.out.println(defaultSDF.format(date));
    }


    /**
     * 获取昨天的 yyyy0MM
     * @return
     */
    public String getYesterday(){
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE,-1);
        return defaultSDF.format(ca.getTime()) ;
    }

    public String yyyyMMdd(){

        return "";
    }

    public String yyyy_MM_dd(){

        return "";
    }

    public String yyyyMM(){

        return "";
    }

    public String yyyy_MM(){

        return "";
    }

    public String toPattern(){

        return "";
    }


    /**
     * 将java.sql.Timestamp对象转化为String字符串
     * @param time
     *            要格式的java.sql.Timestamp对象
     * @param strFormat
     *            输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return 表示日期的字符串
     */
    public static String dateToStr(java.sql.Timestamp time, String strFormat) {
        DateFormat df = new SimpleDateFormat(strFormat);
        String str = df.format(time);
        return str;
    }

    public static void main(String[] args) {
        Object obj = System.currentTimeMillis();

        java.lang.Object timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(dateToStr(timestamp,"yyyyMMdd"));
        System.out.println(new Date(((Timestamp)timestamp).getTime()));
    }

}
