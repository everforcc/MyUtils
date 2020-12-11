package cn.cc.core.date.utils;

import cn.cc.core.date.constant.Constant_Date;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    public static void main(String[] args) {
        findDay();
    }

    SimpleDateFormat defaultSDF = new SimpleDateFormat(Constant_Date.mode_1);
    /**
     * 日历 没用过
     */
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

    // 根据年月日 和星期来对应时间
    public static void findDay(){
        // 年没问题
        // 月从0开始
        // 日没问题
        // 星期天1
        for(int i=1970;i<2021;i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, Constant_Date.December);
            calendar.set(Calendar.DATE, 7);
            if(Constant_Date.Monday==calendar.get(Calendar.DAY_OF_WEEK)){
                System.out.println(calendar.get(Calendar.YEAR));
            }
        }
    }

}
