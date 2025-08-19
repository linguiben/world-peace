package com.jupiter.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-09-16
 */
@Slf4j
public class DateTest {

    // "27 Aug 2024" 转成 "2024-08-27"
    public static String convertDate(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date date = df.parse(dateStr);
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return outputFormat.format(date);
    }

    // support "27 AUG 2024"
    public static String convertDate2(String dateStr) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Test
    public void test() throws ParseException {
        String dateStr = "27 AUG 2024";
        String convertedDate = convertDate(dateStr);
        System.out.println(convertedDate);
    }

    @Test
    public void test2() throws ParseException {
        String dateStr = "27 Aug 2024";
        String convertedDate = convertDate2(dateStr);
        System.out.println(convertedDate);
    }

    @Test
    public void getEveryDayOfWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
//        log.info("Initial Calendar: {}, timeZone: {}", sdf.format(calendar.getTime()), sdf.getTimeZone().getID());

        calendar.set(2024, Calendar.OCTOBER, 30); // 2024-09-08
//        log.info("Initial Calendar: {}, timeZone: {}", sdf.format(calendar.getTime()), sdf.getTimeZone().getID());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        ArrayList<String> dateList = new ArrayList<>(7);
        for (int i = 1; i <= 7; i++) {
            dateList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        log.info("dateList: {}", dateList);
    }

    @Test
    public void dateChanged() {
        //Get day of today
        Calendar cal = Calendar.getInstance();

        //change the year to 2024-01-08
        cal.set(2024, Calendar.JANUARY, 8);
        System.out.println("day of week changed: " + cal.getTime());

        // Mon Jan 08 00:33:47 CST 2024
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // Mon Jan 08 00:33:47 CST 2024
        System.out.println("day of week changed: " + cal.getTime());

    }

    /**
     * @desc IDOS production issue
     * @author Jupiter.Lin
     * @date 2024-11-12
     */
    @Test
    public void getEveryDayOfWeek3() {
        Calendar calendar = Calendar.getInstance();

        Date date = new Date(124, 8, 27, 5, 45, 16);
        System.out.println(date);

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println(calendar.getTime());
    }

    @Test
    public void getEveryDayOfWeek2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // Set time zone explicitly
        Calendar calendar = Calendar.getInstance();

        // Set calendar to the current date and find the next Sunday
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        ArrayList<String> dateList = new ArrayList<>(7); // Initialize with expected size
        for (int i = 0; i < 7; i++) {
            dateList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        log.info("dateList: {}", dateList);
    }

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2006-02-14");
        System.out.println(sdf.format(date));

        //********************************************************************
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 20);

        // System.out.println(c.get(Calendar.DAY_OF_WEEK));//有就正确，没有就错误：输出2026-02-21

        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        //********************************************************************

        System.out.println("入职20周年纪念日派对日期:" + sdf.format(c.getTime()));
    }


    /*
     获取当前日期所在一周的全部日期
     */
    @Test
    public void getCurrentWeekDates() {
        List<LocalDate> weekDates = new ArrayList<>();

        // Get the current date
        LocalDate today = LocalDate.now();

        // Find the start of the week (Sunday)
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        // Add all days of the week to the list
        for (int i = 0; i < 7; i++) {
            weekDates.add(startOfWeek.plusDays(i));
        }

        log.info("Current Week Dates: {}", weekDates);

    }

    @Test
    public void testMath() {
        int i = 1 << 1 | 1 << 2 | 1 << 5;
        System.out.printf("%s == 0x%x\n", i, i);

        int j = 1 << 3;
        System.out.printf("%s == 0x%x\n", j, j);
    }

}
