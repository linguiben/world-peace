package com.jupiter.calc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// mvn -f world-peace-service surefire-report:report test
@SpringBootTest(classes = Calculator.class)
class CalculatorTest {

    @Autowired
    private Calculator calc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sum() {
        assertEquals(3,calc.sum("1", "2"));
    }

    @Test
    @DisplayName("Nested Test Caes")
    void nestedTest(){
        System.out.println("pls refer to: https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested");
    }

    @ParameterizedTest
    @DisplayName("Parameterized test case")
    @ValueSource(ints = {1,2,3})
    void testParameterized(int i){
        System.out.println(i);
    }
}