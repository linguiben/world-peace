package com.jupiter;

import com.jupiter.service.MapTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MapTest.class)
public class LzgApplicationTests {

    @Autowired
    private MapTest mapTest;

    @Test
    public void contextLoads() {
        System.out.println(mapTest);
        System.out.println("key1="+mapTest.getMaps().get("key1"));
    }

}
