package com.jupiter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * SpringBoot项目切面编程之@Aspect https://blog.csdn.net/hkl_Forever/article/details/121563662
 */
@Slf4j
@Aspect
@Component
public class AopAdvice {

    /**
     * 有两种方式定义切点。1)切点表达式 2)指定需要切入的Annotation
     * 指定切入点表达式
     * 类型 返回值 包.类.方法
     * public * com.*.controller..*(..))
     * 若切点表达式错误，则报: java.lang.IllegalArgumentException:
     * warning no match for this type name: com.jupiter.calc
     */
    @Pointcut("execution (public * com.jupiter.calc..*(..))")
    public void pointCut(){
        /*此处无需任何代码*/
    }

    /**
     * 指定注解切入
     * @param @annotation(xxx)：xxx是自定义注解的全路径
     */
    @Pointcut("@annotation(com.jupiter.annotation.myAnnotation)")
    public void withAnnotationMethods() {
    }

    @After("pointCut()")  // 指定为@line28的pointCut().
    public void afterAdvice(){
        System.out.println("After Advice...222");
    }

    @Before("pointCut()")
    public void beforeAdvice(){
        System.out.println("before Advice...111");
    }

    /**
     * <p>环绕增强切入</p>
     * 表达式和注解方式同时满足才会切入
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //业务操作同 @Before 方式

        long startTime = System.currentTimeMillis();
        //方法执行之前动作，等效于@Before
        Object res = point.proceed();
        //方法执行之后动作，等效于@After

        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
//        log.info("测试切入{}成功，是否包含注解："+method.isAnnotationPresent(Calculator.class), "@Around");
//        log.info("注解中的属性值："+method.getAnnotation(FieldConvert.class).codeType());

        log.info("执行方法耗时(毫秒)为：" + (System.currentTimeMillis() - startTime));
        return res;
    }
}
