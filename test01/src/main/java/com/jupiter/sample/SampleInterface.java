/**
 * @className SampleInterface
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-10 20:28
 */
package com.jupiter.sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-10 20:28
 */
public class SampleInterface extends Father
        implements MyInterface{
    // 1.访问访问可以变大
    // 2.返回类型可以缩小
    // 3.传入参数类型必须一致
    // 4.抛出异常可以变小
    // 5.可以加 final synchronized，不能加 static
    // 实现(重写)接口的方法时，返回值类型可以缩小(是子类)，因为调用时返回的属于父类型就ok
    // 传入的参数类型需要完全一致，若不一致则不是重写了。这是因为到实际调用方法时，可以传入子类的实例。
    // 抛出的异常范围不能比接口大
    @Override
    public final synchronized ArrayList showName(List list) throws InterruptedException{
        return null;
    }

    // 重写父类的方法，访问范围只能扩大，不能缩小
    // public > protected > default > private
    @Override
    protected final synchronized ArrayList showColly(List list) throws InterruptedException {
        this.showColly(new ArrayList<>());
        return null;
    }
}

interface MyInterface{
    List showName(List list) throws InterruptedException;
}

class Father{
    List showColly(List list)throws InterruptedException{
        return list;
    }

    List showColly(Collection list)throws InterruptedException{
        return null;
    }
}

class Name{
    private String name;
    public String getName() {
        return name;
    }
    public Name(String name){
        this.name = name;
    }
}