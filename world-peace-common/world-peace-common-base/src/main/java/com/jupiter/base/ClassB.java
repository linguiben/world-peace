package com.jupiter.base;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ClassB {
//    @Autowired
    private ClassA classA;

    public ClassB(ClassA a){
        this.classA = a;
        System.out.println("ClassB newed");
    }
}
