package com.jupiter.service;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-09 20:57
 */
@ToString
@Component
public class UserService {
    @Value("${sender_address}")
    private String sendFrom;

    @Value("#{appConfig}")
    private Object object;

    @Value("${JAVA_HOME}")
    private String bashName;

    @Autowired
//    @Lazy
    private OrderService orderService;

    @Async
    public void test(){

    }

}
