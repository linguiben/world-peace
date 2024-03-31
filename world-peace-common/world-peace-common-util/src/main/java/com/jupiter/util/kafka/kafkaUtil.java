/**
 * @className kafkaUtil
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-30 18:26
 */
package com.jupiter.util.kafka;

import com.jupiter.annotation.KafkaSendTo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-03-30 18:26
 */
@Slf4j
@Aspect
@Component
public class kafkaUtil {

    private final KafkaProducerDemo kafkaProducerDemo;

    public kafkaUtil(KafkaProducerDemo kafkaProducerDemo) {
        this.kafkaProducerDemo = kafkaProducerDemo;
    }

    @AfterReturning(value = "@annotation(sender)", returning = "methodResult")
    public void afterReturning(JoinPoint joinPoint, KafkaSendTo sender, Object methodResult) {
        if (sender == null) {
            log.warn("注解为空或没有标注此注解");
            // throw new RuntimeException("注解为空或没有标注此注解");
            return;
        }
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
        String[] topics = sender.value();
        log.info("得到注解KafkaSender，注解值:{} 方法返回值:{}", Arrays.asList(topics),methodResult.toString());

        Arrays.stream(topics).distinct().forEach(topic -> {
                    kafkaProducerDemo.sendMessage(topic, methodResult.toString());
                    log.info("msg sent to topic {}", topic);
                }
        );

    }


}
