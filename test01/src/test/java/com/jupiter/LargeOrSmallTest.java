package com.jupiter;

import com.jupiter.branch.LargeOrSmall;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@Slf4j
public class LargeOrSmallTest {

    @Test
    public void diceTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext-test.xml");
        LargeOrSmall dice = context.getBean("largeOrSmall", LargeOrSmall.class);
//        String result = dice.dice(10);
        assertEquals(LargeOrSmall.Large,dice.dice(6),"6 !=> Large");
        assertEquals(LargeOrSmall.Small,dice.dice(1),"1 !=> small");
        assertThrowsExactly(UnsupportedOperationException.class,() -> dice.dice(100),"100 !=> exception");

    }
}
