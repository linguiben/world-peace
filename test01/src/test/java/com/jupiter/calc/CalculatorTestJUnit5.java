package com.jupiter.calc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Junit5
//@SpringJUnitConfig(locations = "classpath:spring.xml")
@SpringJUnitConfig(locations = "classpath:ApplicationContext-test.xml")
class CalculatorTestJUnit5 {

    static Calculator staticCalc;

    @Autowired
    private Calculator calculator;

    @BeforeAll
    static void setCalculator() {
        staticCalc = new Calculator("static_001");
        System.out.println("Connected to Kafka, start to test...");
    }

    @org.junit.jupiter.api.BeforeEach
    void createResource() {
        // calculator = new Calculator("00001");
    }

    //    @Transactional  fall back db
    @org.junit.jupiter.api.AfterEach
    void fallBack() {
        calculator = null;
    }

    // @org.junit.Test(expected = NullPointerException.class)
    @org.junit.jupiter.api.Test
    @DisplayName("Test Calculator sum method")
    /*public needed for JUnit4*/ void sum() {
        assertEquals(7, staticCalc.sum("3", "4"));
        assertEquals(3, calculator.sum("1", "2"));
        assertEquals(5, calculator.sum("2", "3"));
    }

    //            Assumptions.assumeTrue();
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}