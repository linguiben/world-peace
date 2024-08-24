/**
 * @className GenericityTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-10 13:19
 */
package com.jupiter.basic.genericity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-03-10 13:19
 */

/**
 * 测试泛型类型的使用。
 * PECS原则： Producer extends, Consumer super
 */

@Slf4j
public class GenericityTest {
    // 创建带有泛型集合的方法ArrayList<Object>
    public void testGeneric(List<Object> list) {
        list.add(new Object()); // ok
        list.stream().forEach(System.out::println);
    }

    /**
     * 报错。clash with
     * 1. 泛型方法在运行时会进行类型擦除，所以 test(ArrayList<Object>)、test(ArrayList<Integer>)冲突，两者并非重载。
     */
//    public void test(ArrayList<Integer> list) {
//        list.add(Integer.valueOf(100));
//    }

    // 2. 验证: ArrayList<Integer>并非ArrayList<Object>的子类，前者作为实参传入时报错。
    @Test
    public void test1() {
         List<Integer> list = new ArrayList<>();
        // 报错 Required Type: List<Object>  Provided: List<Integer>
        // incompatible types: java.util.List<java.lang.Integer> cannot be converted to java.util.List<java.lang.Object>
        // testGeneric(list);

        // ok
        List<Object> objects = new ArrayList<>();
        testGeneric(objects);
    }

    // 3. 验证: extends的泛型集合 不能添加元素。原因是JDK为了保证泛型的类型不被篡改。
    public void extendsGeneric(List<? extends Object> list) {
        // 报错: Required Type: capture of ? extends Object       Provided: Interger
        list.remove(Integer.valueOf(2)); // 可以删除

        // java: incompatible types: java.lang.Integer cannot be converted to capture#1 of ? extends java.lang.Object
        // list.add(Integer.valueOf(1));// 不能添加，报错。原因是传入的可能是 List<Double>。
        // 反过来，List<? super Integer>的泛型集合是否能添加，不能删除？可能不会出现这种情况
        list.stream().forEach(System.out::println);
    }

    @Test
    void test2(){
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.234d);
        extendsGeneric(doubleList);
    }

    // 测试List<? super Integer>的泛型集合是否能添加，不能删除?
    public void superGeneric(List<? super Integer> list) {
        list.remove(Integer.valueOf(100)); // 经测试以下均ok
        list.add(Integer.valueOf(200));
        log.info(list.toString());
    }

    @Test
    void test3(){
        List<Object> list = new ArrayList<>();
        superGeneric(list);
    }
}
