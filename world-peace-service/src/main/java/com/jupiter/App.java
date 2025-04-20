package com.jupiter;

import com.jupiter.calc.Calculator;
import com.jupiter.redis.RedisDao;
import com.jupiter.io.bio.server.Server;
import com.jupiter.mvc.service.MapTest;
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
@ComponentScan({"com.jupiter.calc", "com.jupiter.service", "com.jupiter.config","com.jupiter.server"
        ,"com.jupiter.redis","com.jupiter.aop","com.jupiter.controller"})
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        // 2. 查看容器内的beans
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.asList(names).stream().forEach(System.out::println);
        Server server = ctx.getBean(Server.class);
        server.start();
        MapTest mapTest = ctx.getBean(MapTest.class);
        System.out.println(mapTest);

        // 3. redisDao
        System.out.println("==============redis test==============");
        RedisDao redisDao = ctx.getBean(RedisDao.class);
        redisDao.demo();

        // 4. AOP
        System.out.println("==============aop test==============");
        Calculator calculator = ctx.getBean(Calculator.class);
        System.out.println(calculator.getMv());
    }
}
