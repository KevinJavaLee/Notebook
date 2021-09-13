package com.kevin.Chpater2;

import java.util.ArrayList;

/**
 * @author Vinlee Xiao
 * @Classname ArrayListMultiThread
 * @Description ArrayList 在并发情况下 出现下表越界情况
 * @Date 2021/9/8 20:01
 * @Version 1.0
 */
public class ArrayListMultiThread {
    static ArrayList<Integer> a1 = new ArrayList<Integer>(10);

    public static class AddTread implements Runnable{

        public void run() {
            for (int i = 0; i < 100000; i++) {
                a1.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddTread());
        Thread t2 = new Thread(new AddTread());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a1.size());
    }
}
