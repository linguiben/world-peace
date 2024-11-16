package com.jupiter.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-11-12
 */
public class DateTest1_8 {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance(); // TimeZone.getTimeZone("Asia/Shanghai")
        calendar.setTime(new Date(124, 9, 11));

        System.out.println("1.initialized: " + calendar.getTime() + " === " + calendar);

        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//        calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 3);
        System.out.println(calendar);
        System.out.println(calendar.getTime());

        System.out.println("2.getTime: " + calendar);
    }
}
