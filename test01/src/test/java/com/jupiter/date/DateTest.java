package com.jupiter.date;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void test() throws ParseException {
        String dateStr = "27 AUG 2024";
        String convertedDate = convertDate(dateStr);
        System.out.println(convertedDate);
    }
}
