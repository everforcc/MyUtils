package cc.core.date;

import cc.core.date.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class DateTest {

    @Test
    void t_1(){
        try {
            long before = DateUtils.time();
            System.out.println(DateUtils.strParseToDate("20200826"));
            System.out.println(DateUtils.now());
            System.out.println(DateUtils.time());
            long end = DateUtils.time();
            System.out.println("2001/1000: " + 2001/1000);
            System.out.println(DateUtils.timedif(before, end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
