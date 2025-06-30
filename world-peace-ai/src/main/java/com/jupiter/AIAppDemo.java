package com.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/29
 */
@Slf4j
@SpringBootApplication
public class AIAppDemo {
    public static void main(String[] args) {
        // SpringApplication.run(com.jupiter.App.class,args);

        SpringApplication app = new SpringApplication(AIAppDemo.class);
        //Banner.Mode.OFF 关闭
        log.info("SpringApplication将以关闭Banner模式启动.");
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);

    }
}
