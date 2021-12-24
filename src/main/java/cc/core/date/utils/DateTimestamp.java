package cc.core.date.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimestamp {

    public static void main(String[] args) {
        timestampToDateStr(1640234215L * 1000);
        System.out.println(timeStamp());
        System.out.println(1640234215-1640230214);
        //SDFformat();
    }

    // 时间戳
    public static void timestampTransFormDate(){

    }

    // 统计format的集合
    private static void SDFformat(){
        /**
         *  10位 秒级别时间戳
         *  13位 毫秒级别时间戳
         *
         */

        // 10位的秒级别的时间戳
        long time1 = 1527767665;
        String result1 = DatePattern.defaultSDF_mode_2.format(new Date(time1 * 1000));
        System.out.println("10位数的时间戳（秒）--->Date:" + result1);
        System.out.println("--->");

        double time2 = 1515730332000d;

        String result2 = DatePattern.defaultSDF_mode_2.format(time2);
        System.out.println("13位数的时间戳（毫秒）--->Date:" + result2);
        System.out.println("--->");

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("13位 --> currentTimeMillis:" + currentTimeMillis);
        String s = DatePattern.defaultSDF_mode_2.format(new Date(currentTimeMillis));
        System.out.println("13位 --> 格式化 : " + s);
        System.out.println("--->");
        System.out.println(DatePattern.defaultSDF_mode_1.format(new Date(currentTimeMillis)));

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
     * @param format 自定义正则，从constant取出
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


}
