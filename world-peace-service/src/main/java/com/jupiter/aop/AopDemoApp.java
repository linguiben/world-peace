/**
 * @className AopDemoApp
 * @desc 使用spring AOP的方式:
 * 1. 引入spring-boot-starter-aop
 * 2. 编写切面类(@Aspect)，并定义切点(@Pointcut(execution or @annotation)) 和 切面类型(Before/After/Around等)
 * 切面类型有5种:
 * 1、@Before：前置增强，在某个JoinPoint执行前的增强
 * 2、@After：final增强，不管抛异常还是正常退出都执行的增强
 * 3、@AfterReturning：后置增强，方法正常退出时执行
 * 4、@AfterThrowing：异常抛出增强，抛出异常后执行
 * 5、@Around：环绕增强，包围一个连接点的增强，最强大的一个方式，且常用
 * Spring常用注解参考: https://blog.csdn.net/m0_46845579/article/details/126685527
 * SpringBoot Dao层常用注解: https://blog.csdn.net/a519525531/article/details/117669373
 * @author Jupiter.Lin
 * @date 2024-02-27 19:21
 */
package com.jupiter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @author Jupiter.Lin
 * @desc AOP Demo类
 * @date 2024-02-27 19:21
 */

@SpringBootApplication
public class AopDemoApp {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AopDemoApp.class, args);
    }
}

/**
 * 定义一个Annotation，用于标记到需要被增强的方法上
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyAnnotation {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default true;
}

@Controller
@RequestMapping("/jupiter")
class MyController {
    @Autowired // 默认按照Bean类型注入，可配合@Qualifier默认按名称注入
    // @Resource 默认按照Bean名称注入
    // @Inject
    private MyService myService;

    @RequestMapping("/aopIndex")
    @ResponseBody // 返回jason格式。否则返回(非jsp格式的)index视图(需引入thymeleaf模板引擎)，视图默认放在resources/templates/
    public String aopIndex() {
        // return "index";
        return this.myService.showMeTheMoney();
    }

    @MyAnnotation("insert msg into kafka request-topic")
    @RequestMapping("/annotationAOP")
    @ResponseBody
    public String annotationAOP(@RequestParam(value = "msg", defaultValue = "null") String message){
        // curl localhost:8080/jupiter/annotationAOP?msg=abcd
        return "null".equals(message) ? "request format: ?msg=xxx" : "msg="+message;
    }
}

@Service
class MyService {
    public String showMeTheMoney() {
        return "1000_000_000.00$";
    }
}

// @Mapper mybatis 3.4.0支持。MyBatis-Spring-Boot-Starter默认会scan这个注解。
@Repository
class MyDao {
    private String queryDB() {
        return "1000";
    }
}


/**
 * 切面类。详细定义了需要增加的切面、切点、切入类型等
 */
@Slf4j
@Aspect
@Component
class AspectAdvice {

    /**
     * 有两种方式定义切点。1)切点表达式 2)指定需要切入的Annotation
     * 指定切入点表达式
     * 类型 返回值 包.类.方法
     * public * com.*.controller..*(..))
     * 若切点表达式错误，则报: java.lang.IllegalArgumentException:
     * warning no match for this type name: com.jupiter.calc
     */
    @Pointcut("execution (public * com.jupiter.aop.*Service.*(..))")
    public void expressionPointCut() {
        /*此处无需任何代码*/
    }

    /**
     * 指定注解切入
     *
     * @param @annotation(xxx)：xxx是自定义注解的全路径
     */
    @Pointcut("@annotation(com.jupiter.aop.MyAnnotation)")
    public void annotationPointCut() {
    }

    @Before("expressionPointCut()")
    public void beforeAdvice() {
        log.info("Before Advice...111");
    }

    @After("expressionPointCut()")  // 指定为@line28的pointCut().
    public void afterAdvice() {
        System.out.println("After Advice...222");
    }

    /**
     * 环绕增强切入
     * 这样写，则表达式和注解方式同时满足才会切入:
     * @Around(value = "expressionPointCut() && annotationPointCut()")
     *
     * 满足表达式即可切入：@Around(value = "expressionPointCut()")
     */
    @Around(value = "expressionPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 行写方法执行之前动作，等效于@Before
        Object res = point.proceed(); // 执行增强的方法
        // 行写方法执行之后动作，等效于@After

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        // log.info("测试切入{}成功，是否包含注解："+method.isAnnotationPresent(Calculator.class), "@Around");
        // log.info("注解中的属性值："+method.getAnnotation(FieldConvert.class).codeType());
        log.info("环绕增强执行耗时(ms): " + (System.currentTimeMillis() - startTime));
        return res;
    }

    /**
     * 获取到method上的注解，方法一
     *  通过切点 -> Method -> 得到注解
     */
    @Around("@annotation(com.jupiter.aop.MyAnnotation)")
    public Object aroundAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Object proceed = null; // 定义一个执行后的结果，用于执行增强后的方法的返回
        // 获取方法上的注解
        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
        if (myAnnotation != null) {
            String value = myAnnotation.value(); // 获取注解信息
            log.info("注解值:"+value); // 执行业务逻辑

            // 在此处写执行前增强(相当于@Before)
            proceed = joinPoint.proceed(); // 执行被增强的方法，也就是原方法。
            // 在此处写执行后增强(相当于@After)
        }

        return proceed; // 必须要将执行后的结果返回给原方法，否则前端请求得不到respond
    }

    /**
     * 获取到method上的注解，方法二:
     * 在增强方法上定义一个类型为目标注解的参数，并将参数名写在@After()中
     */
    @After("@annotation(myAnnotation)")
    public void afterAnnotation(JoinPoint joinPoint,MyAnnotation myAnnotation) {
        if (myAnnotation == null) {
            log.warn("注解为空，或没有此注解");
            throw new RuntimeException("注解为空，或没有此注解");
        }

        String value = myAnnotation.value();
        log.info("得到注解MyAnnotation，注解值:"+value);

    }
}