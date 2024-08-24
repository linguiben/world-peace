/**
 * @className ClassExample
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-08-24 12:46
 */
package com.jupiter.basic.clazz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * author: Jupiter.Lin
 * date: 2024-08-24 12:46
 * desc: 1.验证子类和父类中方法的调用顺序
 * 2.java中利用子类调用父类的方法只能用super.method()，别无他法。
 */

class Animal {
    public void eat() {
        System.out.println("Animal is eating");
    }
}
class Dog extends Animal {
    public void eat() {
        System.out.println("Dog is eating");
    }
}
// 哈巴狗类
class HaBaDog extends Dog {
    public void eat() {
        System.out.println("HaBaDog is eating");
        // 孙子类调用爷爷类的eat方法
        super.eat(); // 调用父类 Dog 的 eat 方法
        // super.super.eat(); // 调用爷爷类 Animal 的 eat 方法 -- 错误的语法，实际不能这么用。
    }
}

@Slf4j
public class ClassExample {
    /**
     * 测试反射调用父类的方法，不成功。
     */
    @Test
    public void testInvokeSuperMethodFromChild() {
        Animal animal = new Dog();
        animal.eat(); // 多态，父类引用指向子类实例，执行的是子类的eat方法
        // 即使利用反射，调用的还是子类的eat方法，简而言之，无法利用子类对象调用父类的被Overwrite方法。c++可以，但是Java不允许。
        try {
            Class<?> clazz = animal.getClass().getSuperclass();
            System.out.println(animal.getClass());
            System.out.println(animal.getClass().getSuperclass());
            Method method = Class.forName("com.jupiter.lambda.LambdaExample$Animal")
                    .getMethod("eat");  // 得到父类的方法
            log.info("method: {}", method); // 打印出父类的方法
            method.invoke(animal); // 实际调用的还是子类的方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试调用父类的方法
     * 不要在父类构造器中调用会被子类的Override方法，因为子类对象还没有初始化完成。会得到null值。
     * 子类对象的初始过程：
     * （1）初始化父类中的静态成员变量和静态代码块，按照在程序中出现的顺序初始化；
     * （2）初始化子类中的静态成员变量和静态代码块，按照在程序中出现的顺序初始化；
     * （3）初始化父类的普通成员变量和执行构造代码块，再执行父类的构造方法；
     * （4）初始化子类的普通成员变量和执行构造代码块，再执行子类的构造方法；
     *
     * 测试子类和父类中方法的调用顺序
     * 1.父类静态代码块
     * 2.子类静态代码块 (这是由于加载子类之前，需要先加载父类)
     * 3.父类实例代码块
     * 4.父类构造方法
     * 5.子类实例代码块
     * 6.子类构造方法
     *
     * // load -> link -> initialize
     * new Apple()
     * -> 加载父类 -> 加载子类 // 静态成员和静态方法同级，也就是按照代码顺序
     * -> 执行子类构造方法(第一行默认super()) -> 执行父类构造方法(构造方法里面优先执行普通代码块)
     * -> 执行父类普通代码块 -> 父类构造方法的其他行代码  // 构造方法里面优先执行了普通代码块和非静态属性(两者统计，按代码写的顺序)
     * -> 执行子类普通代码块 -> 子类构造方法的其他行代码
     */
    @Test
    public void testMethodExecuteSequence() {
        new Apple();
        new Apple();
    }
}
class Fruit {
    static { System.out.println("Fruit static block"); } // 1.父类静态代码块
    static String name = "A Fruit";                     // 2.父类静态属性
    { System.out.println("Fruit instance block"); }     // 7.构造方法执行时，先执行普通代码块
    Integer price = 10;                                 // 8.和普通属性
    public void describe() {
        System.out.println("This is a Fruit!");
    }
    public Fruit() {                                    // 6.子类会调用父类的构造方法
        System.out.println("Fruit constructor");        // 9.然后执行其他行代码
    }

}
class Apple extends Fruit {
    static { System.out.println("Apple static block"); } // 3.子类静态代码块
    static String name = "An Apple";                    // 4.子类静态属性
    { System.out.println("Apple instance block"); }     // 10.构造方法执行时，先执行普通代码块
    Integer price = 20;                                 // 11.和普通属性
    @Override
    public void describe() {
        System.out.println("This is an Apple!   !");
    }
    public Apple() {                                   // 5.类加载完成，开始执行构造方法
        System.out.println("Apple constructor");        // 12.执行其他行代码
    }
}


