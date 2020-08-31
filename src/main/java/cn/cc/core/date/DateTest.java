package cn.cc.core.date;

import cn.cc.core.date.utils.DateUtils;

import java.text.ParseException;

public class DateTest {

    public static void main(String[] args) {
        // Date_Timestamp.timestampTransFormDate();
        try {

            System.out.println(DateUtils.strParseToDate("20200826"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
