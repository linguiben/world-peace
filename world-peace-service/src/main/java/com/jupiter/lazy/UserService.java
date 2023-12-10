package com.jupiter.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-18 13:41
 */
@Component
public class UserService {
    @Autowired
    private OrderService orderService;

//    @Async
    public void test(){

    }
}

