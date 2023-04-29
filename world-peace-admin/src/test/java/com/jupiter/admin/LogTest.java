package com.jupiter.admin;

import org.junit.Test;

import java.util.*;

public class LogTest {

    public void t01() {
//        new Logger
    }

    List<Integer> list = Arrays.asList(1, 2, 3);

    @Test
    public void t02() {
        for (int i = 0, lenth = list.size(); i < lenth; i++) {
            // do sth...
        }

        Map<String, String> map = new HashMap<>();
        map.put("01", "Cecilia");
        map.put("02", "Joey");
        map.put("03", "Gigi Lai");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getValue());
        }
    }

}