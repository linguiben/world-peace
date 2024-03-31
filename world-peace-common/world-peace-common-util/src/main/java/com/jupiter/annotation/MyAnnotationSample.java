package com.jupiter.annotation;

import java.lang.annotation.*;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-16 13:10
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotationSample {
}
