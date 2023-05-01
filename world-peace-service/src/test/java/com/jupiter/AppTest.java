package com.jupiter;

import com.jupiter.calc.MapTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapTest.class)
public class AppTest {
    @Autowired
    private MapTest mapTest;

    @Test
    public void contextLoads() {
        System.out.println(mapTest.toString());
        System.out.println("key1="+mapTest.getMaps().get("0001.HK"));
    }

}
