package com.jupiter.test;

import com.jupiter.config.AppConfig;
import com.jupiter.service.DummyService;
import com.jupiter.service.OrderService;
import com.jupiter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-09 20:34
 */
public class T01 {

    @Test
    public void testPropertiesValue() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        System.out.println(userService);
    }

    /**
     * test where @Bean can work
     */
    @Test
    public void test$Bean(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService);
        System.out.println(orderService);
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig.orderService());

        System.out.println(context.getBean(DummyService.class));
    }

}
