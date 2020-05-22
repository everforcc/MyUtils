package cn.cc.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * 时间工具类
 */
public class ToolTime {

/**
 * 以这个方法为主
 */


/**********格式化日期**********/
    /**
     * 跟定的需要时间类型的正则表达式，默认 yyyy-MM-dd
     * yyyy-MM-dd hh:mm
     * yyyyMMddHHmmss
     */
    public static String pattern="yyyy-MM-dd";
    /**
     * 格式化日期
     * @param voucherdate
     * @return
     * @throws Exception
     */
    public String formatDate(String voucherdate)throws Exception{
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
     * 根据正则返回当前时间
     * @param regex
     * @return
     */
    public static String nowTime(String regex){
        return new SimpleDateFormat(regex).format(new Date(System.currentTimeMillis()));
    }
/**********格式化日期**********/

/**********时间戳**********/

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
    public static String timeStamp2Date(String seconds,String format) {
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

/**********时间戳**********/

/**********日历**********/
    /**
     * 日历？
     */
    public void   calendarDate(){
        Date date = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.SECOND, 2);
        date = cd.getTime();
        System.out.println(date);
    }
/**********日历**********/

    public static void main(String[] args) {
        String timeStamp = timeStamp();
        System.out.println("timeStamp="+timeStamp); //运行输出:timeStamp=1470278082
        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数

        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
        System.out.println("date="+date);//运行输出:date=2016-08-04 10:34:42

        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(timeStamp2);  //运行输出:1470278082
    }

}
