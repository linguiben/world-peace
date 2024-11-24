package com.jupiter.agent;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024/11/24
 */

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 这里可以对类文件进行修改
        System.out.println("Transforming class: " + className);
        return classfileBuffer; // 返回未修改的字节码
    }
}
