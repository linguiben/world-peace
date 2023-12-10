package com.jupiter.test;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2023-12-10 12:01
 */
public class AbstractClassTest {
    public abstract static class AbstractInnerClass {
        public abstract void method();
    }

    public static void main(String[] args) {
        // 创建匿名内部类继承自抽象类
        // 在静态方法中，它要求以上抽象类也是静态的
        AbstractInnerClass innerClass = new AbstractInnerClass() {
            @Override
            public void method() {
                System.out.println("This is an anonymous inner class method.");
            }
        };
        // 调用匿名内部类的方法
        innerClass.method();
    }

    /**
     * The abstract inner class without static
     */
    public abstract class AbstractDog{}

    public void testAbstractMethod(){
        AbstractDog dog = new AbstractDog() {};
        AbstractCat cat = new AbstractCat() {};
        new AbstractInnerClass() {
            @Override
            public void method() {}
        };
        // new Thread(()->{});
    }
}

/**
 * this is required to be none modifier
 */
class AbstractCat{}