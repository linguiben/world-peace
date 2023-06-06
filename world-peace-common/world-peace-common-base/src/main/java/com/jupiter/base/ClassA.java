package com.jupiter.base;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ClassA {
//    @Autowired
    private ClassB classB;

    public ClassA(ClassB b){
        this.classB = b;
        System.out.println("ClassA newed.");
    }
}
