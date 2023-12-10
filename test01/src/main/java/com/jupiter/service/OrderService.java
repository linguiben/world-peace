package com.jupiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-10 23:46
 */
public class OrderService {

    @Autowired
    private UserService userService;

    private String orderId = "abcd9876";

    //@PostConstruct
    @Bean
    public DummyService dummyService(){
        System.out.println("abcdefg");
        return new DummyService();
    }

    //@Async
    public void test(){

    }
}
