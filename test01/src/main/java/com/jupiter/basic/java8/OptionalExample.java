/**
 * @ClassName Example
 * @Desc TODO
 * @Author Jupiter.Lin
 * @Date 2024-08-24 15:04
 */
package com.jupiter.basic.java8;

import org.apache.commons.lang3.StringUtils;
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

        name = "jupiter";
        // 如果名称不为null且不为空，则将其转换驼峰式，否则返回默认值
        String aDefault =
                Optional.ofNullable(name).filter(StringUtils::isNotBlank).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).orElse("default");// Jupiter
        System.out.println("aDefault = " + aDefault);
    }
}
