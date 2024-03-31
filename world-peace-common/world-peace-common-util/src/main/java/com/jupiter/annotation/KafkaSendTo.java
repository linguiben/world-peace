package com.jupiter.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author Jupiter.Lin
 * @desc 标注上此注解，将自动将数据写入到kafka
 * @date 2023-07-16 13:10
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KafkaSendTo {
    String key() default ""; // the key to be sent to kafka topics

    @AliasFor("value")
    String[] topics() default {};

    @AliasFor("topics")
    String[] value() default {};// the kafka topic names

}
