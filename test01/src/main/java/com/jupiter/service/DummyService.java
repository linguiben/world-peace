package com.jupiter.service;

import org.springframework.context.annotation.Bean;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-10 23:52
 */
public class DummyService {

    @Bean
    public OrderService orderService(){
        return new OrderService();
    }
}
