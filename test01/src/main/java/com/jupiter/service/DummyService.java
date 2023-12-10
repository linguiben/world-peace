package com.jupiter.service;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-10 23:52
 */
public class DummyService implements ImportBeanDefinitionRegistrar {

    @Bean
    public OrderService orderService(){
        return new OrderService();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition ();
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setBeanClass(OrderService.class);
        registry.registerBeanDefinition ( "orderService123", beanDefinition);
    }

    // quick sort
}
