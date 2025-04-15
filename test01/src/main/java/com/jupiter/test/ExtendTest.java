package com.jupiter.test;

/**
 * @desc: Test the usage of key word extends in Java
 * @author: Jupiter.Lin
 * @date: 2025/2/2
 */
public class ExtendTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class Parent {
    public String publicName = "publicName"; // 1.所有类可见
    protected String protectedName = "protectedName"; // 2.package-private + 子类可见
    String defaultName = "defaultName"; // 3.有时也称为包私有 package-private
    private String privateName = "privateName"; // 4.仅本类可见

    public void publicMethod() {
        System.out.println("publicMethod -- " + this.publicName);
    }

    protected void protectedMethod() {
        System.out.println("protectedMethod");
    }

    void defaultMethod() {
        System.out.println("defaultMethod");
    }

    private void privateMethod() {
        System.out.println("privateMethod");
    }

    public Parent() {
        System.out.println("Parent Constructor");
    }
    public void print() {
        System.out.println("ParentPrint");
    }
}

class Child extends Parent {
    public String publicName = "publicName2";
    protected String protectedName = "protectedName2";
    String defaultName = "defaultName2";
    private String privateName = "privateName2";

    public void publicMethod() {
        System.out.println("publicMethod2 -- " + this.publicName);
    }
    protected void protectedMethod() {
        System.out.println("protectedMethod2");
    }
    void defaultMethod() {
        System.out.println("defaultMethod2");
    }
    private void privateMethod() {
        System.out.println("privateMethod2");
    }
}

class MainTest {
    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(child.publicName); // 打印的是Child类的publicName
        System.out.println(child.protectedName);
        System.out.println(child.defaultName);
        // System.out.println(child.privateName); // private属性只能在本类中访问
        child.publicMethod(); // 调用的是Child类的publicMethod
        child.protectedMethod();
        child.defaultMethod();
        // child.privateMethod(); // private方法只能在本类中访问
        child.print();

        System.out.println("====================================");
        if(child instanceof Parent) {
            Parent parent = (Parent) child; // 向上转型
            /** 多态性指的是方法的多态性，而成员变量没有多态性。
             * 1. 成员变量（字段）绑定: 编译时绑定，即在编译时就已经确定了，与引用变量的实际类型无关，
             *    即成员变量没有多态性，它们的访问基于引用类型的静态绑定。
             * 2. 方法调用绑定: 使用的是动态绑定（也称为运行时绑定），即在运行时根据对象的实际类型来确定调用的方法，而非根据引用类型来确定。
             *    这意味着即使你将子类对象赋值给父类引用，调用的方法仍然是子类中重写的方法。
             */
            System.out.println(parent.publicName); // 打印的是Parent类的publicName，成员变量的访问是基于引用类型的静态绑定
            System.out.println(parent.protectedName);
            System.out.println(parent.defaultName);
            // System.out.println(parent.privateName); // private属性只能在本类中访问
            parent.publicMethod(); // 调用的是Child类的publicMethod: 打印 publicMethod2 -- publicName2
            parent.protectedMethod();
            parent.defaultMethod();
            // parent.privateMethod(); // private方法只能在本类中访问
            parent.print();
        }
    }
}