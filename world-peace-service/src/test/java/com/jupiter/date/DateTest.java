package com.jupiter.date;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

/**
 * @desc: Test first day of the week in JAVA 21
 */
public class DateTest {
    @Test
    public void getFirstDayOfWeek() {
        System.out.println(
                Calendar.getInstance().getFirstDayOfWeek()
        );
    }
}
