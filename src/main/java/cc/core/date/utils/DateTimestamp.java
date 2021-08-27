package cc.core.date.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimestamp {

    // 时间戳
    public static void timestampTransFormDate(){
        // 10位的秒级别的时间戳
        long time1 = 1527767665;
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time1 * 1000));
        System.out.println("10位数的时间戳（秒）--->Date:" + result1);
        Date date1 = new Date(time1*1000);   //对应的就是时间戳对应的Date    // 13位的秒级别的时间戳
        double time2 = 1515730332000d;
        String result2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time2);
        System.out.println("13位数的时间戳（毫秒）--->Date:" + result2);

        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        System.out.println(s);
        System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())));


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
