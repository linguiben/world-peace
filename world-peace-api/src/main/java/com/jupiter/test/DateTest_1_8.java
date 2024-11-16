package com.jupiter.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @desc: Test on-line hot update locale
 * @author: Jupiter.Lin
 * @date: 2024-11-12
 */
public class DateTest_1_8 {

    public static void main(String[] args) throws InterruptedException {

        //DateTest_1_8 testCase = new DateTest_1_8();
        // 将locale更改为马尔代夫
        //Locale maldivesLocale = Locale.forLanguageTag("en-MV");
        //Locale.setDefault(maldivesLocale);

        // sleep 5秒后将本地化信息Locale设置为GB - United Kingdom (Great Britain)
//        new Thread(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            Locale.setDefault(Locale.UK);
//        }).start();

        Date date = new Date(124, 9, 18); // Mon Oct 14 00:00:00 CST 2024

        while (true) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);

            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            System.out.println(Locale.getDefault() + " " + calendar.getTime()/* + " " + calendar*/);

            Thread.sleep(1000);
        }

    }

}
