package com.jupiter;

import com.jupiter.calc.Calculator;
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
@ComponentScan({"com.jupiter.calc","com.jupiter.aop"})
@EnableAutoConfiguration
public class AppAOPDemo {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext ctx = SpringApplication.run(AppAOPDemo.class, args);

        // 2. 查看容器内的beans
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.asList(names).stream().forEach(System.out::println);

        // 4. AOP
        System.out.println("==============aop test==============");
        Calculator calculator = ctx.getBean(Calculator.class);
        System.out.println(calculator.getMv());
    }
}
