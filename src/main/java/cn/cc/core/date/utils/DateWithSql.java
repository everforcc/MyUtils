package cn.cc.core.date.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWithSql {

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

    // sql的时间和date的时间不同，转换有问题的话可以用如下代码

    public static void main(String[] args) {
        Object obj = System.currentTimeMillis();

        Object timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(dateToStr(timestamp,"yyyyMMdd"));
        System.out.println(new Date(((Timestamp)timestamp).getTime()));
    }

}
