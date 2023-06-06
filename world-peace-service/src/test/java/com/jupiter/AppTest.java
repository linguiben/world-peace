package com.jupiter;

import com.jupiter.service.MapTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MapTest.class)
@SpringBootTest
@EnableConfigurationProperties(MapTest.class)  //开启MapTest的配置绑定功能，并将其注册到容器中
public class AppTest {
    @Autowired
    private MapTest mapTest;

    @Test
    public void contextLoads() {
        System.out.println(mapTest.toString());
        System.out.println("key1="+mapTest.getMaps().get("0001.HK"));
    }

}
