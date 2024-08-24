/**
 * @ClassName OptionalExample
 * @Desc TODO
 * @Author Jupiter.Lin
 * @Date 2024-08-24 15:04
 */
package com.jupiter.basic.java8;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Author: Jupiter.Lin
 * Date: 2024-08-24
 * Description: 测试Optional类的使用
 */
public class OptionalExample {

    @Test
    public void test1() {
        String name = null;
        Optional.ofNullable(name).map(String::toUpperCase).ifPresent(System.out::println);
        Optional.ofNullable(name).orElse("default");
        String aDefault = Optional.ofNullable(name).map(String::toUpperCase).orElse("default");
        System.out.println(aDefault);
    }
}
