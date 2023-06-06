package com.jupiter;

import com.jupiter.calc.Say;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;


class Test001 {

    @DisplayName("嵌套测试")
    @Test
    void t01(){
        System.out.println("this is the first test case.");
    }

    @Test
    void t02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext-test.xml");
        Map stockPriceMap = context.getBean("stockPriceMap", HashMap.class);
        System.out.println(stockPriceMap);
    }

    @Test
    void t03(){
        new Say().sayHappy();
    }
}



