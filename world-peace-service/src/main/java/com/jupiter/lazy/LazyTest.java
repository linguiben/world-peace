package com.jupiter.lazy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-18 13:36
 */

@SpringBootApplication
//@SpringBootConfiguration
//@ComponentScan("com.jupiter.lazy")
//@EnableAutoConfiguration
@EnableAsync
@Slf4j
public class LazyTest {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LazyTest.class);
        UserService userService = ctx.getBean(UserService.class);
        log.info("LazyTest - userService: {}", userService);

        SpringApplication.exit(ctx);
    }
}
