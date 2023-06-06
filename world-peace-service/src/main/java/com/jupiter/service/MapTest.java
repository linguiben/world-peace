package com.jupiter.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
//@Configuration // 告诉SpringBoot这个是一个配置类 == 配置文件 beans.xml
//@PropertySource(value = {"classpath:/application.yml"}, encoding = "utf-8") //有的人是写了这个注解能成功,但是我这边不能有这个注解,有的话,就连编译都会报错
@ConfigurationProperties(prefix = "tes")
public class MapTest {
    private Map<String, String> maps;
}