package com.jupiter;

import com.jupiter.calc.Calculator;
import com.jupiter.service.MapTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MapTest.class)
@SpringBootTest(classes = App.class)
//@EnableAutoConfiguration
//@EnableConfigurationProperties(MapTest.class)  //开启MapTest的配置绑定功能，并将其注册到容器中
@Slf4j
@Disabled
public class AppTest {
    @Autowired(required = false)
    private MapTest mapTest;
    @Autowired(required = false)
    private ApplicationContext ctx;

    @Test
    public void contextLoads() {
        log.info("mapTest: {}",mapTest.toString());
        System.out.println("key1="+mapTest.getMaps().get("0011.HK"));
        Assertions.assertEquals("340",mapTest.getMaps().get("0011.HK"));
    }

    @Disabled
    @Test
    public void testAppStartup(){
//        ApplicationContext ctx = SpringApplication.run(App.class);
//        App app = ctx.getBean(App.class);
//        log.info("app: {}", ctx);
    }

    @Test
    void testCalculator(){
        Calculator calc = ctx.getBean(Calculator.class);
        calc.sum("1","2","3");
        BigDecimal indexValue = calc.getIndexValue();
        log.info("indexValue: {}",indexValue);
        log.info("lastTradeIndexValue: {}",calc.getLastTradIndexValue());
    }

}
