package com.jupiter;

import com.jupiter.domain.Car;
import com.jupiter.stock.StockDistributor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WorldPeaceCommonUtilApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(WorldPeaceCommonUtilApplication.class, args);
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        System.out.println(run.getBean("stockDistributor", StockDistributor.class));

        // 经测试发现以下3个bean都被参数绑定了(market=HK)
        System.out.println(run.getBean("marketInfo2")); // @Bean注入
        System.out.println(run.getBean("stock-com.jupiter.stock.MarketInfo")); // @EnableConfigurationProperties注入
        System.out.println(run.getBean("RT_SS")); // beans.xml + @ImportResource注入

        Car car =new Car(null,"benz","red",400000.0);
        SqlSession sqlSessioin = run.getBean("sqlSession", SqlSession.class);
        sqlSessioin.insert("save",car);
    }

}
