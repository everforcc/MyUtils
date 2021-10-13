package cc.webapi.baidu.netdisk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author everforcc 2021-10-12
 */
public class DateUtils {

    public static String format(String time){
        Date date = new Date(Long.valueOf(time) * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

}
