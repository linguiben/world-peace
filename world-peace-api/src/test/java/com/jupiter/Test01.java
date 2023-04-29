package com.jupiter;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

class Test02{
    static final int i = 10;
    static int j = 20;

}
class Test03{
    public static void main(String[] args) throws InterruptedException {
        Test02 t02 = null;
        System.out.println(Test02.i);
        Thread.sleep(30_000);
        System.out.println("done");
    }
}


public class Test01 {

    static Test01 t = new Test01();
    static int i = 1;
    static int j = 2;
    public Test01(){
        System.out.println("i+j=" + (i+j));
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Test02.i);
        Thread.sleep(60_000);
        //Test01 t = new Test01();
        System.out.println("ok");
    }

    @Test
    public void split(){
        // iiippp:12345/abcde;
        Scanner scanner = new Scanner(System.in);
        System.out.println("url:");
        String url = scanner.nextLine();
        String[] strings = url.split(":",2);
        System.out.println(Arrays.toString(strings));

        //零宽断言
        System.out.println(Arrays.toString(url.split(":|(?=/)",3)));
    }

    @Test
    public void t02(){
        int [][] array = new int[3][2];
        array [0][0]=1;
        array [0][1]=2;
        array [1] = array[0];
        array [0]= new int[4];
        array[0][0]=10;
        System.out.println(array[1][0]);

    }
}
