package com.jupiter.agent;

import com.jupiter.test.ChangeLocale;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent loaded at JVM startup");
        // 注册一个Transformer来修改类
        inst.addTransformer(new MyClassFileTransformer());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException {
        System.out.println("Agent loaded at runtime");

        Class.forName(ChangeLocale.class.getName()); // change locale to UK

        // 注册一个Transformer来修改类
        inst.addTransformer(new MyClassFileTransformer());
    }
}