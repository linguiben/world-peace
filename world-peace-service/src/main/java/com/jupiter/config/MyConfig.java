package com.jupiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true) // 这是一个SpringBoot配置类 == beans.xml, proxyBeanMethods默认为true (控制bean为单例)
public class MyConfig {

    /**
     * @Bean 默认为单例，原理是容器中拿到的是MyConfig的代理类
     * @return
     */
    @Bean
    public String lili(){
        return new String("Lili");
    }

    @Bean("beanName")
    public String asa(){
        return new String("Asa");
    }

}
