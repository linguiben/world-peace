package com.jupiter.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

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
    public void lockTest(){
        // TODO
        // FIXME
        // HACK

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            System.out.println("do sth with locking");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println("unlock");
        }

    }

    @Test
    public void arrayListTest(){
        int[] a = new int[]{1,2,3,4};
        // ArrayList<String> list = new ArrayList<>(Arrays.asList("a","b","c"));
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

//        System.out.println(Arrays.toString(a));
        System.out.println(list.toString());

        List<String> subList = list.subList(0, 1);
        System.out.println(subList);

//        subList.add("d");
//        System.out.println(subList + "====" + list);

//        subList.clear();
//        System.out.println(subList + "====" + list);

        list.add("e");
        System.out.println(list);
        System.out.println(subList);
    }
    @Test
    public void mapTest(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");

        Set<String> keySet = map.keySet();
        System.out.println(keySet);

        // UnsupportedOperationException
        // keySet.add("6");

        map.put("5", "d");
        System.out.println(keySet);
    }

    @Test
    public void t01(){
        System.out.println( "Hello test001!" );
    }

    @Test
    public void testMap() {
        HashMap<String,String> map = new HashMap<>();
        map.put("aaa","哥哥");
        map.put("bbb", "妹妹");
        map.put("ccc", "姐姐");
        map.put("ddd", "阿姨");
        HashMap<String,String> map2 = (HashMap<String, String>) map.clone();
        HashMap<String,String> map3 = (HashMap) map.clone();
        System.out.println("=======java 8 query map=========");
        map.forEach((k,v)->System.out.print(k +":" +v));
        map.entrySet().stream().forEach(System.out::println);
        map.entrySet().stream().parallel().forEach(entry -> {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
        System.out.println("=======1=========");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            if ("aaa".equals(entry.getKey())) {
                it.remove();
                continue;
            }
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getValue());
        }
        System.out.println("===========2===========");
        try{
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                if ("aaa".equals(entry.getKey())) {
                    map2.remove(entry.getKey());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }
//        System.out.println(entry.getValue());
        System.out.println("===========2===========");
        List<Integer> list = Arrays.asList(1, 2, 2, 4);
        list.forEach(System.out::print);
        ArrayList<Integer> list2 = new ArrayList<>(list);
        //        list.add(4);
        list2.add(5);
        //int size = list2.size(); IndexOutOfBoundsException
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).equals(2))
                list2.remove(i);
            System.out.println("list2.size(): "+list2.size());
        }
        System.out.println(list2);
        System.out.println(Arrays.toString(new int[]{4,3,5,9}));
    }
    @Test
    public void mergeSortTest(){
        class inner{
            public inner(){}
            public int [] merge(int[] a,int[] b){
                int[] array = new int[a.length + b.length];
                for(int i=0,j=0,k=0;k<array.length;k++){
                    if(i >= a.length){
                        array[k] = b[j++];
                    }else if(j >=b.length){
                        array[k] = a[i++];
                    }else if(a[i] <= b[j]){
                        array[k] = a[i++];
                    }else {
                        array[k] = b[j++];
                    }
                }
                return array;
            }

            public int [] mergeSort(int[] arr){
                if(arr.length < 2){
                    return arr;
                }
                int middle = arr.length >> 1;
                int [] left = Arrays.copyOfRange(arr,0,middle);
                int [] right = Arrays.copyOfRange(arr,middle,arr.length);

                //分别判断左右
                int[] a = this.mergeSort(left);
                int[] b = this.mergeSort(right);

                return this.merge(a,b);
            }
        }
        System.out.println("abc");
        inner inner = new inner();
        int [] a = {3,6,2,4,9,7};
        int [] b = {1};
//        System.out.println(Arrays.toString(inner.merge(a, b)));
        System.out.println(Arrays.toString(inner.mergeSort(a)));
    }
    @Test
    public void t02() throws IOException {
        String str = "abc";

        int input = System.in.read();
        System.out.println(str.getClass().getName() + "@" + Integer.toHexString(str.hashCode()));

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

    @Test
    public void t04(){
        Father son = new Son();
        System.out.println(son.getClass().getSimpleName()); // Son
        System.out.println(son.name); // 牛魔王
        son.fire(); // I can flame
    }

    class Father {
        String name = "牛魔王";
        void fire(){
            System.out.println("I can bite");
        }
    }

    class Son extends Father{
        String name = "红孩儿";
        @Override
        void fire() {
            System.out.println("I can flame");
        }
    }

}
