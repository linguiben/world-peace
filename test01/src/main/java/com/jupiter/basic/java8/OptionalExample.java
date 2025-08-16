/**
 * @ClassName Example
 * @Desc TODO
 * @Author Jupiter.Lin
 * @Date 2024-08-24 15:04
 */
package com.jupiter.basic.java8;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Author: Jupiter.Lin
 * Date: 2024-08-24
 * Description: 测试Optional类的使用，优雅处理null值
 */
public class OptionalExample {

    @Test
    public void test1() {
        String name = null;
        // 1. 如果名称不为空，则将其转换为大写并打印
        Optional.ofNullable(name).map(String::toUpperCase).ifPresent(System.out::println);
        // 2. 如果名称为空，则返回默认值
        name = Optional.ofNullable(name).orElse("default");
        System.out.println(name);

        // 3. 传统方法处理null值
        Boolean isEmpty = name != null && name.length() > 0;
        System.out.println("isEmpty ?: " + isEmpty);

        name = "jupiter";
        // 4. 如果名称不为null且不为空，则将其转换驼峰式，否则返回默认值
        String aDefault =
                Optional.ofNullable(name)
                        .filter(StringUtils::isNotBlank)
                        .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)) // Jupiter
                        .orElse("default");
        System.out.println("aDefault = " + aDefault);

        // 5. 使用Optional处理嵌套对象
        User user = User
                .builder()
                .address(Address.builder().country(
                                Country.builder().countryName("CN").build()).build())
                .build();

        String CNName = Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCountry)
                .map(Country::getCountryName)
                .orElse("china");
        Assertions.assertEquals("CN", CNName);
    }
}

@Data
@Builder
class User {
    private Integer userId;
    private String userName;
    private Address address;
}

@Data
@Builder
class Address {
    private Integer addressId;
    private String addressName;
    private Country country;
}

@Data
@Builder
class Country {
    private Integer countryId;
    private String countryName;
}


