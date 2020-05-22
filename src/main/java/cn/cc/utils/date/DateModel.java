package cn.cc.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 地址:
 * https://blog.csdn.net/fanrenxiang/article/details/80531649
 *
 */
public class DateModel {

    /**
     * 日期，待补充
     */
    public void   testCalendar(){
        Date date = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.SECOND, 2);
        date = cd.getTime();
        System.out.println(date);
    }

     public static void now(){
         System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
     }

    /**
     * 注：以下的方法中很多常量和方法我都没有提取出来，正式项目中还是建议大家封装在时间处理类中，规范化操作
     * @param args
     */
    public static void main(String[] args) {
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

}
