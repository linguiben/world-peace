package com.jupiter;

import com.jupiter.server.Server;
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
@ComponentScan("com.jupiter")
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext applicationContext = SpringApplication.run(App.class, args);

        // 2. 查看容器内的beans
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.asList(names).stream().forEach(System.out::println);
        Server server = applicationContext.getBean(Server.class);
        server.start();
//        MapTest mapTest = applicationContext.getBean(MapTest.class);
//        System.out.println(mapTest);
    }
}
