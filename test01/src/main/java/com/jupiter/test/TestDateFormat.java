/**
 * @ClassName TestDateFormat
 * @Desc TODO
 * @Author Jupiter.Lin
 * @Date 2024-08-27 01:17
 */
package com.jupiter.test;

/**
 * Author: Jupiter.Lin
 * Date: 2024-08-27
 * Description: 
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class TestDateFormat {
//    public static void main(String[] args) {
//        String inputDateString = "25 AUG 2024";
//        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy");
//        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            // 解析输入的日期字符串
//            Date parsedDate = inputFormat.parse(inputDateString);
//
//            // 将解析后的日期格式化为 "yyyy-MM-dd"
//            String formattedDate = outputFormat.format(parsedDate);
//
//            System.out.println("Formatted Date: " + formattedDate);
//        } catch (ParseException e) {
//            // 处理解析异常
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        new TestDateFormat().test();
        new TestDateFormat().test2();

        String inputDateString = "25 Aug 2024";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // 解析输入的日期字符串
            LocalDate parsedDate = LocalDate.parse(inputDateString, inputFormatter);

            // 使用输出格式器格式化 LocalDate 对象
            String formattedDate = parsedDate.format(outputFormatter);

            System.out.println("Formatted Date: " + formattedDate);
        } catch (DateTimeParseException e) {
            // 处理解析异常
            System.err.println("Invalid date format: " + inputDateString);
            e.printStackTrace();
        }
    }

    void test() {
        String dateString = "25 Aug 2024";

        // Define the DateTimeFormatter with the appropriate pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        try {
            // Parse the string to LocalDate
            LocalDate date = LocalDate.parse(dateString, formatter);
            System.out.println("Converted LocalDate: " + date);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }

    void test2() {
        String dateString = "25 Aug 2024";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd MMMM uuuu")
                .toFormatter(Locale.ENGLISH);

        LocalDate localDate = LocalDate.parse(dateString, formatter);
        System.out.println(localDate); // 输出: 2024-08-25
    }
}
