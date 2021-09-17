package cc.core.date.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author guokailong 2021-09-15
 */
public class DateCompare {

    private static Date today = new Date();
    private static Date before = new Date(today.getTime()-1);
    private static Date next = new Date(today.getTime()+1);

    private static Calendar calendarToday = Calendar.getInstance();
    private static Calendar calendarBefore = Calendar.getInstance();
    private static Calendar calendarNext = Calendar.getInstance();
    static {
        calendarToday.setTime(today);
        calendarBefore.setTime(before);
        calendarNext.setTime(next);
    }

    public static void main(String[] args) {
        //compareTo();
        //afterBefore();
        //calenderCompare();
        newAPI();
    }

    //1. compareTo
    public static void compareTo(){
        System.out.println(" today.compareTo(before) ");
        System.out.println(today.compareTo(before));
        System.out.println(" today.compareTo(today) ");
        System.out.println(today.compareTo(today));
        System.out.println(" today.compareTo(next) ");
        System.out.println(today.compareTo(next));
    }

    //2. after equals before
    public static void afterBefore(){
        System.out.println(today.after(before));
        System.out.println(today.after(today));
        System.out.println(today.after(next));
        System.out.println(today.equals(today));
        System.out.println(today.before(next));
    }

    //3. Calender
    public static void calenderCompare(){
        System.out.println(calendarToday.after(calendarBefore));
        System.out.println(calendarToday.after(calendarToday));
        System.out.println(calendarToday.after(calendarNext));
        System.out.println(calendarToday.before(calendarToday));
        System.out.println(calendarToday.equals(calendarNext));
    }

    //4. time long类型直接比较
    public static void getTimeCompare(){
        //
        long timeToday = today.getTime();
        long timeBefore = before.getTime();
        long timeNext = next.getTime();
        if(timeToday>timeBefore){

        }
    }

    //5. java8 api
    public static void newAPI(){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate before = LocalDate.of(2021, 9, 14);
        LocalDate today = LocalDate.of(2021, 9, 15);

        System.out.println(before.isAfter(today));
        System.out.println(before.isBefore(today));
        System.out.println(before.isEqual(today));

        System.out.println(before.compareTo(before));
        System.out.println(before.compareTo(today));
        System.out.println(today.compareTo(before));
    }

}
