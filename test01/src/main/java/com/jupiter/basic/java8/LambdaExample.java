/**
 * @className LambdaExample
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-08-24 00:37
 */
package com.jupiter.basic.java8;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jupiter.Lin
 * @desc 测试Lambda表达式的十种常用
 * @date 2024-08-24 00:37
 */
@Slf4j
public class LambdaExample {
    // 创建一个默认的List
    final List<String> list = Arrays.asList("apple", "orange", "banana");

    /**
     * 1. 使用lambda表达式遍历List
     */
    @Test
    public void test1() {
        // 原先的遍历方式
        for (String item : list) {
            System.out.println(item);
        }
        // 使用lambda表达式遍历List
        list.forEach(item -> System.out.println(item));
        // 使用forEach方法遍历List
        list.forEach(System.out::println);
    }

    /**
     * 2. 使用lambda排序
     */
    @Test
    public void test2() {
        Object o = (Object) list;
        System.out.println(o);
        log.info("排序前list的地址:{}", ((Object) list).toString());
        list.sort((s1, s2) -> s1.compareTo(s2));
        log.info("第一次排序后list的地址:{}", list);
        list.forEach(System.out::println); // 升序输出：apple orange banana

        Collections.sort(list, (s1, s2) -> s2.compareTo(s1));
        log.info("第二次排序后list的地址:{}", list);
        list.forEach(System.out::println); // 降序输出: orange banana apple
    }

    /**
     * 3. 使用lambda过滤
     */
    @Test
    public void testFilter() {
        // 使用lambda表达式过滤List
        list.stream().filter(item -> item.length() > 5).forEach(System.out::println);
        List<String> collect = list.stream().filter(item -> item.length() > 5).collect(Collectors.toList());
        System.out.println(list == collect);
    }

    /**
     * 4. 使用lambda映射 (map)
     */
    @Test
    public void testMap() {
        List<String> collect = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 5. 使用lambda进行规约 (reduce)
     */
    @Test
    public void testReduce() {
        // 得到-apple-orange-banana
        String result = list.stream().reduce("", (acc, item) -> acc + "-"+ item);
        System.out.println(result);
    }

    /**
     * 6. 使用lambda进行分组 (groupBy)
     */
    @Test
    public void testGroupBy() {
        // 根据字符串长度进行分组
        list.stream().collect(Collectors.groupingBy(String::length)).forEach((key, value) -> System.out.println(key + ":" + value));
    }

    /**
     * 7. 使用lambda进行查找 (find)
     */
    @Test
    public void testFind() {
        // 查找第一个满足条件的元素
        String result = list.stream().filter(item -> item.length() > 5).findFirst().orElse("");
        System.out.println(result);

        // 查找最后一个满足条件的元素
        String lastResult = list.stream().filter(item -> item.length() > 5).reduce((acc, item) -> item).orElse("");
        System.out.println(lastResult);
    }

    /**
     * 8. 使用lambda进行匹配 (match)
     */
    @Test
    public void testMatch() {
        // 判断是否至少有一个元素满足条件
        boolean anyMatch = list.stream().anyMatch(item -> item.length() > 5);
        System.out.println(anyMatch);

        // 判断是否所有元素满足条件
        boolean allMatch = list.stream().allMatch(item -> item.length() > 5);
        System.out.println(allMatch);

        // 判断是否没有元素满足条件
        boolean noneMatch = list.stream().noneMatch(item ->item.length() > 5);
    }

    /**
     * 9. 使用lambda创建函数式接口 (创建线程)
     */
    @Test
    public void testCreateFunctionalInterface() {
        Runnable runnable = () -> System.out.println("Hello, world!");
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 10. 使用lambda和Optional避免NPE
     */
    @Test
    public void testAvoidNPE() {
        String name = null;
        // 使用Optional避免NPE
        String result = Optional.ofNullable(name).orElse("Unknown");
        System.out.println(result);
    }

    /**
     * 11. 使用lambda和Stream处理复杂数据结构
     */
    @Data
    @RequiredArgsConstructor
    class Person {
        private final String name;
        private final int age;
    }
    @Test
    public void testComplexDataStructure() {
        List<Person> people = Arrays.asList(
                new Person("John", 30),
                new Person("Jane", 25),
                new Person("Jim", 40)
        );

        // 根据年龄对人员进行分组
        Map<Integer, List<Person>> groupedPeople = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        // 打印分组结果
        groupedPeople.forEach((age, persons) -> {
            System.out.println("Age: " + age);
        });
    }


}


