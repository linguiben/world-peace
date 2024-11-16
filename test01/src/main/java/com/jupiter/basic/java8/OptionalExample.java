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
        // 如果名称不为空，则将其转换为大写并打印
        Optional.ofNullable(name).map(String::toUpperCase).ifPresent(System.out::println);
        // 如果名称为空，则返回默认值
        Optional.ofNullable(name).orElse("default");
        // 如果名称为空，则返回默认值
        String aDefault = Optional.ofNullable(name).map(String::toUpperCase).orElse("default");
        System.out.println(aDefault);
    }
}
