package com.jupiter.test;

import com.jupiter.spring.config.AppConfig;
import com.jupiter.spring.service.DummyService;
import com.jupiter.spring.service.OrderService;
import com.jupiter.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-07-09 20:34
 */
public class T01 {

    @Test
    public void testArrayList(){
        List<String> asList = Arrays.asList("Apple", "Banana", "Cherry");
        ArrayList<String> list = new ArrayList<String>(asList);
        // asList.add("abcd");
        list.add("abcd");
        System.out.println(asList);
        System.out.println(list);
    }

    @Test
    public void testPropertiesValue() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService);
    }

    @Test
    public void testGenricType(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ConcurrentHashMap bean = (ConcurrentHashMap)context.getBean("testConcurrentHashMap",
                java.util.concurrent.ConcurrentHashMap.class);
        System.out.println(bean);
        bean.put("abcd",123);
        System.out.println(bean.get("abcd"));
    }

    /**
     * test where @Bean can work
     */
    @Test
    public void test$Bean(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService);
        System.out.println(orderService);
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig.orderService());

        System.out.println(context.getBean(DummyService.class));
    }

    /**
     * test @Lazy 解决循坏依赖
     */
    @Test
    public void test$Lazy() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);
    }

    public static void main(String[] args) {
        // 先创建Runnable对象，再传递给Thread创建多个线程，最终只有一个Runnable对象
        PVTest pvTest = new PVTest();
        for (int i = 0; i < 3; i++) {
            new Thread(pvTest).start();
        }
        /* running result:
        Thread-1com.jupiter.test.PVTest@79259089 is waiting...
        Thread-2com.jupiter.test.PVTest@79259089 is waiting...
        Thread-0com.jupiter.test.PVTest@79259089 do sth...
        Thread-0com.jupiter.test.PVTest@79259089 done
        Thread-1com.jupiter.test.PVTest@79259089 do sth...
        Thread-1com.jupiter.test.PVTest@79259089 done
        Thread-2com.jupiter.test.PVTest@79259089 do sth...
        Thread-2com.jupiter.test.PVTest@79259089 done
         */
    }

    @Test
    public void testPV(){
        // 先创建Runnable对象，再传递给Thread创建多个线程，最终只有一个Runnable对象
        PVTest pvTest = new PVTest();
        for (int i = 0; i < 5; i++) {
            new Thread(pvTest).start();
        }
    }

    @Test
    public void test_Lambda_This() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                // lambda 表达式中的this指外部调用该方法的对象，此处是com.jupiter.test.T01@45770dc3
                System.out.println(name + this + " is running...");
            }).start();
        }
    }

}

class PVTest implements Runnable {
    // mutext 信号量、互斥量
    private static volatile AtomicInteger s = new AtomicInteger(1);
    public static final Object lock = new Object();
    public void run() {
        String name = Thread.currentThread().getName();
        s.decrementAndGet();

        // P:
        if (s.get() < 0) {
            System.out.println(name + this + " is waiting...");
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + this + " do sth...");

        // V:
        s.incrementAndGet();
        if (s.get() <= 0) {
            synchronized (lock) {
                lock.notify();
            }
        }
        System.out.println(name + this + " done");
    }

    // lambda 表达式中的this指外部调用该方法的对象，此处是pvTest
    public void star() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + this + " is running...");
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 验证lambda中的this指的是pvTest
//        PVTest pvTest = new PVTest();
//        pvTest.star();

        // 用匿名内部类，会创建3个实现了Runnable的匿名内部类的对象，this指的是这些对象
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            String name = Thread.currentThread().getName();
                            s.decrementAndGet();

                            // P:
                            if (s.get() < 0) {
                                System.out.println(name + this + " is waiting...");
                                synchronized (lock) {
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(name + this + " do sth...");

                            // V:
                            s.incrementAndGet();
                            if (s.get() <= 0) {
                                synchronized (lock) {
                                    lock.notify();
                                }
                            }
                            System.out.println(name + this + " done");
                        }
                    }
            );
            thread.start();
        }
//        Thread.sleep(1000 * 1200);
    }
}

class T002 {
    public static void main(String[] args) {
        String s1 = new String("abcd");
        String s2 = new String("abcd");
        String s3 = "abcd";
        String s4 = "abcd";
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
    }
}
