package com.jupiter.test;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/3/12
 */
public class T02 {
    public static void main(String[] args) {
        // print the jdk version
        System.out.println(System.getProperty("java.version"));
        // use text block to define a sql
        String sql = """
                select *
                from mytable
                where weather = 'snow';
                """;
        System.out.println(sql);
    }
}
