package com.jupiter.spring.config;

import com.jupiter.spring.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-09 20:45
 */

@ComponentScan(value = "com.jupiter.test,com.jupiter.service",includeFilters = @ComponentScan.Filter(type =
        FilterType.ANNOTATION,value = Component.class))
@PropertySource("classpath:mail.properties")
//@Import(OrderService.class)
@EnableAsync
public class AppConfig {

    @Bean
    public OrderService orderService(){
        return new OrderService();
    }
}
