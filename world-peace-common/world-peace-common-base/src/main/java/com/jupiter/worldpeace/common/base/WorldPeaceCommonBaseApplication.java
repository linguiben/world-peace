package com.jupiter.worldpeace.common.base;

import com.jupiter.base.ClassA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jupiter")
public class WorldPeaceCommonBaseApplication {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(WorldPeaceCommonBaseApplication.class, args);
		String[] names = run.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		ClassA classA = run.getBean(ClassA.class);
//		System.out.println(classA);
	}

}
