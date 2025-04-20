package com.jupiter;

import com.jupiter.mvc.service.MapTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
* @Description: 启动类
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午3:45:12
 * 如果报404: Resource not found, 检查是否将controller加入包扫描了
*/

/* 报以下循环依赖错误的原因，是SpringBoot 2.4升级到2.7.18时依赖没有处理赶紧，导致同时依赖了2.4和2.7.18两个版本。
2.4版本存在DataSourceInitializerInvoker.class,而后者已经删除了该类。
The dependencies of some of the beans in the application context form a cycle:
   indexController (field private org.apache.ibatis.session.SqlSession com.jupiter.controller.IndexController.sqlSession)
      ↓
   sqlSessionTemplate defined in class path resource [org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]
      ↓
   sqlSessionFactory defined in class path resource [org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]
┌─────┐
|  dataSource defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]
↑     ↓
|  org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker
└─────┘
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration //(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker.class})
@ComponentScan({"com.jupiter.controller","com.jupiter.calc","com.jupiter.service"})
//@MapperScan("com.jupiter.dao") // mapper包扫描,代替每个@Mapper
@Slf4j
public class AppConfigDemo {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ApplicationContext applicationContext = SpringApplication.run(AppConfigDemo.class, args);

        // 2. 查看容器内的beans
        String[] names = applicationContext.getBeanDefinitionNames();
        log.info("beans list:===========================");
        // Arrays.asList(names).stream().forEach(System.out::println);

        // 3. 检查配置绑定
        MapTest mapTest = applicationContext.getBean(MapTest.class);
        // System.out.println(mapTest);

    }
}
