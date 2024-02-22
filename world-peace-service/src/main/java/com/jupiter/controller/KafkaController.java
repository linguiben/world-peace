/**
 * @className KafkaController
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-06 17:15
 */
package com.jupiter.controller;

import com.jupiter.service.KafkaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-06 17:15
 */
@Slf4j
@RestController
@RequestMapping
public class KafkaController {

    @Autowired
    private KafkaServiceImpl kafkaServiceImpl;

    @GetMapping("/kafka/send/{input}")
    public String sendToKafka(@PathVariable String input){
        kafkaServiceImpl.send(input);
        return input + " sent to kafka.";
    }

    @GetMapping("/kafka/stop")
    public void stopListener(){
        kafkaServiceImpl.stop();
    }
}
