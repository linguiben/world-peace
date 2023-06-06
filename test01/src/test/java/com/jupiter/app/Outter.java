package com.jupiter.app;

//局部内部类
public class Outter {
    private int s=2;

    public void work() {
        final int ss=4;//private int ss=4;参数 ss 的修饰符不合法；只允许使用 final
        class inner {

            public void eat(){
                System.out.println("局部内部类");
                System.out.println(s);//调用外部私有变量
                System.out.println(ss);//调用方法内局部变量（必须得是final修饰的变量）
                //防止局部内部类改变方法内局部变量的值
            }

        }

        inner i=new inner();//创建局部内部类对象
        i.eat();//调用局部内部类方法
        //局部内部类只能在方法内部中使用，一旦方法执行完毕，局部内部类就会从内存中删除

    }//外部类方法中

    public static void main(String[] args) {
        Outter o=new Outter();//创建外部类对象
        o.work();//调用含局部内部类的方法
    }

}