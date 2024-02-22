package com.jupiter.admin;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class AdminApp
{
    public static void main(String[] args) {
        // SpringApplication.run(App.class,args);

        SpringApplication app = new SpringApplication(AdminApp.class);
        //Banner.Mode.OFF 关闭
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);

//        JasyptUtil jasyptUtil = ctx.getBean(JasyptUtil.class);
//        jasyptUtil.encryDemo();
    }


}
