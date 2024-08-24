/**
 * @className iABC
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-08-23 23:13
 */
package com.jupiter.basic.Interfaze;

/**
 *@desc 测试java只允许单继承，但允许多实现。
 *@author Jupiter.Lin
 *@date 2024-08-23 23:13
 */
public class InterfaceABC {}
interface A {
    default void a(){};
    default void sameMethod(){};
}
interface B {
    default void b(){};
    default void sameMethod(){};
}
class C implements A,B{
    // 因为C同时实现了A和B，而A和B有相同的默认方法sameMethod
    // 所以C必须实现A和B的sameMethod方法
    @Override
    public void sameMethod() {
        A.super.sameMethod();
    }
}

interface D extends A, B {
    // 同理，D虽然是接口，但由于D继承了A和B，所以D继承了A和B的sameMethod方法，
    // 也必须实现A和B的sameMethod方法
    @Override
    default void sameMethod() {
        A.super.sameMethod();
    }
}