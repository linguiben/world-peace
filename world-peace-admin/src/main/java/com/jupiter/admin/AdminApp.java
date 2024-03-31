package com.jupiter.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;

/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication
//@EnableWebMvc
public class AdminApp
{
    public static void main(String[] args) {
        // SpringApplication.run(App.class,args);

        SpringApplication app = new SpringApplication(AdminApp.class);
        //Banner.Mode.OFF 关闭
        log.info("SpringApplication将以关闭Banner模式启动.");
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);

//        JasyptUtil jasyptUtil = ctx.getBean(JasyptUtil.class);
//        jasyptUtil.encryDemo();
    }

    // 获取运行时jar包所在路径，linux和windows下通用
    @Bean
    public String applicationHomePath() {
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File jarFile = applicationHome.getSource();
        log.info("applicationHome:{}", applicationHome);
        return jarFile.getParentFile().toString();
    }

}
