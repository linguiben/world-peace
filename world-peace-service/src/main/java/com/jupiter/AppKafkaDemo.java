package com.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
* @Description: 启动类
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午3:45:12
*/
//@SpringBootApplication
@SpringBootConfiguration
@ComponentScan({"com.jupiter.service","com.jupiter.controller"})
@EnableAutoConfiguration
@Slf4j
public class AppKafkaDemo {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext ctx = SpringApplication.run(AppKafkaDemo.class, args);

        // 2. 查看容器内的beans
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.asList(names).stream().forEach(log::info);
    }
}
