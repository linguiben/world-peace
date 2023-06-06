package com.jupiter.calc;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// mvn -f world-peace-service surefire-report:report test
// mvn test -Dtest=com.jupiter.Test001
// mvn test -Dtest=*Test#add,Test*
//@SpringJUnitConfig(locations = "classpath:spring.xml")
@SpringJUnitConfig(locations = "classpath:ApplicationContext-test.xml") // Junit5
@DisplayName("CalculatorJUnit5Test")
class CalculatorJUnit5Test {

    @Autowired
    private Calculator calculator;

    @Autowired
    private Map<?,?> stockPriceMap;

    @BeforeAll
    static void setCalculator() {
        System.out.println("Connected to Kafka, start to test...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("release kafka connection");
    }

    @BeforeEach
    void createResource() {
        // calculator = new Calculator("00001");
    }

    //    @Transactional  fall back db
    @AfterEach
    void fallBack() {
        calculator = null;
    }

    // @org.junit.Test(expected = NullPointerException.class)
    @Test
    @DisplayName("Test Calculator sum method")
    /*public needed for JUnit4*/ void sum() {
        assertEquals(7, calculator.sum("3", "4"));
        assertEquals(3, calculator.sum("1", "2"));
        assertEquals(5, calculator.sum("2", "3"));
    }

    @Timeout(value = 100,unit = TimeUnit.MILLISECONDS)
    @Test
    void performanceTest() throws InterruptedException {
        Thread.sleep(50);
        assertEquals(7, calculator.sum("3", "4"));
    }

    @Disabled
    @Test
    void test001(){
        int i = 1/0;
    }

    @RepeatedTest(3)
    void test002(){
        System.out.println("abcd");
    }

    @Test
    void test003() {
        Assumptions.assumeTrue(false,"skip this test case if false");
        assertEquals(7999, calculator.sum("3", "4"));
    }

    @DisplayName("嵌套测试")
    @Test
    void test004(){
        System.out.println("pls refer to :");
        // @Nested
    }

    @ParameterizedTest
    @DisplayName("Parameterized test case1")
    @ValueSource(ints = {1,2,3})
    void testParameterized(int i){
        System.out.println(i);
    }

    static Stream<String> stockPriceProvider(){
        return Stream.of("apple","bear");
    }
    @ParameterizedTest
    @DisplayName("Parameterized test case2")
    @MethodSource("stockPriceProvider")
    void testParameterized2(String input){
        System.out.println(input);
        assertNotNull(input,"input is null");
    }

    @Test
    void pringstockPriceMap(){
        System.out.println(calculator);
        System.out.println(stockPriceMap);
    }

    // test csv source
    @ParameterizedTest
    // @CsvFileSource(resources = "/tmp/xx.csv", numLinesToSkip = 1)
    // in /test/resources
    @CsvSource({
            "Angelor,Femal",
            "Jack, Male"
    })
    void shouldBeCsvCase(String firstName, String sex){
        System.out.println(firstName +" | "+ sex);
    }


//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }

}