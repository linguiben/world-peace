package com.jupiter.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@ToString
@Component
//@Configuration // 告诉SpringBoot这个是一个配置类 == 配置文件 beans.xml
//@PropertySource(value = {"classpath:/application-prd.yml"}, encoding = "utf-8") //有的人是写了这个注解能成功,但是我这边不能有这个注解,有的话,就连编译都会报错
@ConfigurationProperties(prefix = "tes")
public class MapTest {
    private Map<String, String> maps;

    // 测试
    public static MapTest getInstance() {
        return new MapTest();
    }

    public void test() {
        int i = 0;
        int j = 10;
        while((i=j)<10){
            System.out.println(i);
            i--;
        }
    }
}