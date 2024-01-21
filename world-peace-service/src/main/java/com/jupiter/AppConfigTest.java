package com.jupiter;

import com.jupiter.service.MapTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
* @Description: 启动类
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午3:45:12
 * 如果报404: Resource not found, 检查是否将controller加入包扫描了
*/
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"com.jupiter.controller","com.jupiter.calc","com.jupiter.service"})
//@MapperScan("com.jupiter.dao") // mapper包扫描,代替每个@Mapper
@Slf4j
public class AppConfigTest {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext applicationContext = SpringApplication.run(AppConfigTest.class, args);

        // 2. 查看容器内的beans
        String[] names = applicationContext.getBeanDefinitionNames();
        log.info("beans list:===========================");
        // Arrays.asList(names).stream().forEach(System.out::println);

        // 3. 检查配置绑定
        MapTest mapTest = applicationContext.getBean(MapTest.class);
        // System.out.println(mapTest);

    }
}
