package com.jupiter.util.kafka.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author Jupiter.Lin
 * @desc 标注上次注解，将从kafka topic中读取数据，并填入到变量或者集合中
 * @date 2023-07-16 13:10
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FromKafkaTopic {
    String key() default ""; // the key to be sent to kafka topics

    @AliasFor("value")
    String[] topics() default {};

    @AliasFor("topics")
    String[] value() default {};// the kafka topic names

}
