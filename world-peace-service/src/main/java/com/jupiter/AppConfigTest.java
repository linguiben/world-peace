package com.jupiter;

import com.jupiter.service.MapTest;
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
@ComponentScan("com.jupiter.calc")
@EnableAutoConfiguration
public class AppConfigTest {
    public /*static*/ void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext applicationContext = SpringApplication.run(AppConfigTest.class, args);

        // 2. 查看容器内的beans
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.asList(names).stream().forEach(System.out::println);

        // 3. 检查配置绑定
        MapTest mapTest = applicationContext.getBean(MapTest.class);
        System.out.println(mapTest);
    }
}
