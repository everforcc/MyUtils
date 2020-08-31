package cn.cc.core.date.utils;

import cn.cc.core.date.constant.Constant_Date;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

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

}
