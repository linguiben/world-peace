package com.jupiter.date;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024-09-16
 */
public class DateTest {

    // "27 Aug 2024" 转成 "2024-08-27"
    public static String convertDate(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date date = df.parse(dateStr);
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return outputFormat.format(date);
    }

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
        String dateStr = "27 AUG 2024";
        String convertedDate = convertDate2(dateStr);
        System.out.println(convertedDate);
    }
}
