package com.jupiter.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdvice {

    @Pointcut("execution (public * com.jupiter.calc.*(..))") // 类型 返回值 类.方法
    public void pointCut(){
        System.out.println("pointCut签名。。。");
    }

    @After("pointCut()")
    public void beforeAdvice(){
        System.out.println("beforeAdvice...");
    }
}
