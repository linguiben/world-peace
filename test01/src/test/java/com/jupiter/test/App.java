package com.jupiter.test;

import com.jupiter.test.impl.EmpDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void t01(){
        System.out.println( "Hello test001!" );
    }

    @Test
    public void testGetBean(){
//        EmpDao empDao = new EmpDaoImpl();
//        empDao.addEmp();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        EmpDao empDao = context.getBean("empDao",EmpDao.class);
//        EmpDao empDao = (EmpDao) context.getBean("empDao");
        empDao.addEmp();

    }

    @Test
    public void t03(){
        int[] a = new int[]{1,2,3, 5,5};
        char c = 65;
        try {
            System.out.println(a[-1]);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        System.out.println("c = " + c);
        boolean b1 = true;
        boolean b2 = false;
        System.out.println(Arrays.toString(a));
        if(b2 = true && b1){
            System.out.println("111");
        }else {
            System.out.println(222);
        }
    }

}
