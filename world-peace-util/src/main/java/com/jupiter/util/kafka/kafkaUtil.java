/**
 * @className kafkaUtil
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-30 18:26
 */
package com.jupiter.util.kafka;

import com.jupiter.util.kafka.annotation.FromKafkaTopic;
import com.jupiter.util.kafka.annotation.ToKafkaTopic;
import com.jupiter.util.kafka.impl.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Jupiter.Lin
 * @desc 切面类。拦截@ToKafkaTopic,并将返回值发送给kafka
 * 拦截@FromKafkaTopic，并将从kafka收到的消息写入标注的变量、集合或容器中。
 * @date 2024-03-30 18:26
 */
@Slf4j
@Aspect
@Component
public class kafkaUtil {

    private final Sender kafkaSender;

    public kafkaUtil(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @AfterReturning(value = "@annotation(sender)", returning = "methodResult")
    public void afterReturning(JoinPoint joinPoint, ToKafkaTopic sender, Object methodResult) {
        if (sender == null) {
            log.warn("Cannot find annotation @ToKafkaTopic");
            // throw new RuntimeException("注解为空或没有标注此注解");
            return;
        }
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
        String[] topics = sender.value();
        log.debug("Parsed annotation @ToKafkaTopic，values:{} methodResult:{}", Arrays.asList(topics),
                methodResult.toString());

        // TODO: support key value and ProducerRecord.
        Arrays.stream(topics).distinct().forEach(topic -> {
                    kafkaSender.sendMessage(topic, methodResult.toString());
                    log.debug("msg sent to topic {}", topic);
                }
        );
    }

    /**
     * 方法执行前，从kafka收取message，注入到方法的参数中，需要考虑手动commit？
     * e.g.: call(@FromKafkaTopic list);
     */
    // @Before
    public void injectKafkaMessag(JoinPoint joinPoint, FromKafkaTopic fromKafkaTopic){

    }
}
