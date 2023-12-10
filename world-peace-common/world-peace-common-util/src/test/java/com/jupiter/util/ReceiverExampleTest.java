package com.jupiter.util;

import org.junit.jupiter.api.Test;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-12-10 13:53
 */
class ReceiverExampleTest {

//    @BeforeEach
//    void setUp(){
//
//    }

    @Test
    void testSample() {
        DataDistributor<TextData, TextDataConsumer> distributor = new DataDistributor<>(true);
        TextDataConsumer consumer = new TextDataConsumer();
        consumer.start(); // start to receive and print data

        distributor.addListener(consumer); // distributor data to all receivers
        for(int i=0;i<=4;i++){
            distributor.distributeData(new TextData("data " + i));
        }
    }
}