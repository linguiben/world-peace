package com.jupiter.calc;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class CalculatorTestJUnit4 {

    static Calculator staticCalc;

    @Autowired
    private Calculator calculator;

    @BeforeClass
    public static void setCalculator() {
        staticCalc = new Calculator("static_001");
        System.out.println("Connected to Kafka, start to test...");
    }

    @Before
    public void createResource() {
        // calculator = new Calculator("00001");
    }

    //    @Transactional  fall back db
    @AfterClass
    public static void fallBack() {
        staticCalc = null;
        System.out.println("release kafka connection");
    }

    // @org.junit.Test(expected = NullPointerException.class)
    @Test
    public void sum() {
        assertEquals(7, calculator.sum("3", "4"));
        assertEquals(3, calculator.sum("1", "2"));
        assertEquals(5, calculator.sum("2", "3"));
    }

    //            Assumptions.assumeTrue();
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }

}