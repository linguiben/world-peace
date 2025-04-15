package com.jupiter.test;

import java.lang.reflect.Field;

/**
 * @desc: 测试main()方法的调用
 * @author: Jupiter.Lin
 * @date: 2025/2/5
 */
public class MainMethodTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

/**
 * 把main()方法当成普通方法来调用。递归调用导致栈溢出。
 */
class T003 {
    private  String name = "T003";
    public static void main(String[] args) throws Exception { // 此方法被子类T004隐藏，为了避免编译错误，需要遵守方法重写的规则
        System.out.println("java.version=" + System.getProperty("java.runtime.version")); // 21.0.2
        System.out.println("args.length=" + args.length);
        if (args.length > 0) { // 若注释掉这段，则会递归调用而导致栈溢出
            // the input args
            for (String arg : args) {
                System.out.println("args = " + arg);
            }
            System.out.println("exit 0");
            System.exit(0); // 正常退出，避免递归调用导致栈溢出
        }
        T003.main(new String[]{"end"});  // java.lang.StackOverflowError
    }
}

class T004 extends T003{
    private String name = "T004";
    /**
     * 报错提示方法签名不一致</br>
     * <i>'main(String[])' in 'com.jupiter.test.T004' clashes with 'main(String[])' in 'com.jupiter.test.T003';
     * overridden method does not throw 'java.lang.Exception'</i>
     * <p>虽然说main()方法是程序的入口(属于jvm)，但是它也是一个普通的静态方法，可以被调用，可以被重载（静态方法我们通常说是Hiding，而不是Overriding）。</br>
     * 所以静态方法的重新（或Hiding)要遵守方法重写的规则，即方法签名必须一致，不能降低访问权限，不能抛出更多的异常。</p>
     *
     * <p>注意：多态性指的是方法的多态，而不是属性的多态。具体见 {@link com.jupiter.test.ExtendTest}</p>
     */
    public static void main(String[] args) throws Exception {
        T004 t = new T004();
        System.out.println(t.name); // T004
        T003 t3 = t;
        System.out.println(t3.getClass());

        // 反射获取T003.name
        Field field = null;
        try {
            field = t3.getClass().getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            System.out.println(field.get(t3)); // T003
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}

/**
 * 方法隐藏(Hiding): 隐藏仅适用于静态方法，不适用于实例方法。
 * 1. 方法签名：子类隐藏的方法必须与父类方法具有相同的方法签名（方法名和参数列表）
 * 2. 调用方式：静态方法的调用是基于引用的类型，而不是对象的实际类型。
 * 3. 访问修饰符：子类隐藏的方法不能降低父类方法的可见性。
 * 4. 返回类型：从Java 5开始，支持协变返回类型（covariant return type），即子类隐藏的方法的返回类型可以是
 * 5. 异常：子类隐藏的方法不能抛出比父类方法更多的异常。
 */
class Father {
    public static void staticMethod() throws RuntimeException {
        System.out.println("Father static method");
    }
}

class Son extends Father {
    public static void staticMethod() throws RuntimeException {
        System.out.println("Son static method");
    }
}