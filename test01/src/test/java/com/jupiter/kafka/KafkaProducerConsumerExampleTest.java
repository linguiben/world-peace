package com.jupiter.kafka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-11-25 15:08
 */
class KafkaProducerConsumerExampleTest {

    KafkaProducerConsumerExample testCase;
    @BeforeEach
    void setUp() {
        testCase = new KafkaProducerConsumerExample();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showTopics() {
        try {
            Set topics = testCase.showTopics(testCase.producerProps);
            for (Object topic : topics) {
                System.out.println(topic);
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}