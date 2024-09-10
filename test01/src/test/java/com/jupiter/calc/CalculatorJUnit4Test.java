package com.jupiter.calc;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class) // Junit4
@ContextConfiguration("classpath:ApplicationContext-test.xml")
// @RunWith(Parameterized.class)
// @Transactional
public class CalculatorJUnit4Test {

    private double expected;
    private double stockPrice;

    @Parameterized.Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Double[][]{
                {15d, 1.5}
                , {20d, 2.0}
                , {null, null}
        });
    }

//    public CalculatorJUnit4Test(Double expected, Double input){
//        this.expected = expected;
//        this.stockPrice = input;
//    }

    @Test
    // @Rollback(value = true)
    public void testAvg(){
         assertEquals(this.expected,this.stockPrice * 10);
    }

    @Autowired
    private Calculator calculator;

    @BeforeClass
    public static void setCalculator() {
        System.out.println("Connected to Kafka, start to test...");
    }

    @Before
    public void createResource() {
        // calculator = new Calculator("00001");
    }

    //    @Transactional  fall back db
    @AfterClass
    public static void fallBack() {
        System.out.println("release kafka connection");
    }

    // @org.junit.Test(expected = NullPointerException.class)
    @Test
    public void sum() {
        assertEquals(7, calculator.sum("3", "4"));
        assertEquals(3, calculator.sum("1", "2"));
        assertEquals(5, calculator.sum("2", "3"));
    }

    @Test(timeout = 2000)
    public void performanceTest() throws InterruptedException {
        Thread.sleep(199);
        assertEquals(7, calculator.sum("3", "4"));
    }

    @Test(expected = ArithmeticException.class)
    public void exceptionTest(){
        int i = 1/0;
    }

    //            Assumptions.assumeTrue();
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }

}