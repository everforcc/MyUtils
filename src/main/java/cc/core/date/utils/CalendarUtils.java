package cc.core.date.utils;

import cc.constant.ConstantDate;

import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class CalendarUtils {

    public static void main(String[] args) {
        //indDay();
        //calendarDate();
        //findDay();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 从某个时间，计算某段时间前/后的时间
     * 提供一个示例，需要了自己修改
     */
    public static void calendarDate(){
        Date date = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.YEAR,-1); // 年
        cd.add(Calendar.DATE,0); // 天
        cd.add(Calendar.MONTH,0); // 月，等等
        cd.add(Calendar.SECOND, 0);//秒
        date = cd.getTime();
        System.out.println(DatePattern.defaultSDF_mode_2.format(date));
    }

    /**
     * 获取昨天的
     * @return yyyy-MM-dd
     */
    public static String getYesterday(){
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE,-1);
        return DatePattern.defaultSDF_mode_1.format(ca.getTime()) ;
    }

    /**
     * 找某一段时间 比如知道是周六20号，那就查最近几年有这种日期的
     * 根据 年/月/日/星期来对应时间 某几个条件
     */
    private static void findDay(){
        // 年没问题
        // 月从0开始
        // 日没问题
        // 星期天 1,,星期一 2
        Calendar calendar = Calendar.getInstance();
        for(int i=1970;i<2021;i++) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, ConstantDate.December);
            calendar.set(Calendar.DATE, 7);
            // 获取给定日期是周几，判断是否是周一
            if(ConstantDate.Monday==calendar.get(Calendar.DAY_OF_WEEK)){
                System.out.println(calendar.get(Calendar.YEAR));
            }
        }
    }

    /**
     * Calendar
     * 年：是多少就写多少
     * 月：1月是0,12月是11
     * 周：周日1，周一2，周六7
     * 日：也就是号，每月几号，1号2号
     * month/day 建议使用ConstantDate里面定义好的，不容易弄错
     */


    public static void findDay(int startYear,int endYear,int month){
        Calendar calendar = Calendar.getInstance();

        // TODO 可能还有其他情况，目前只遇到过这一种
        // FIXME 写的不够完善
        for(int i=startYear;i<=endYear;i++) {
            // 年 循环
            calendar.set(Calendar.YEAR, i);
            // 月 12月
            calendar.set(Calendar.MONTH, ConstantDate.December);
            // 号 7号
            calendar.set(Calendar.DATE, 7);
            // 循环判断那一年的12月7号是周一
            // Calendar.DAY_OF_WEEK获取当前时间是周几
            // 获取给定日期是周几，判断是否是周一
            if(ConstantDate.Monday==calendar.get(Calendar.DAY_OF_WEEK)){
                System.out.println(calendar.get(Calendar.YEAR));
            }
        }
    }

}
