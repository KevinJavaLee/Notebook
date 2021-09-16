package com.kevin.Chpater4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Vinlee Xiao
 * @Classname AtomicIntegerArray
 * @Description AtomicIntegerArray
 * @Date 2021/9/16 16:20
 * @Version 1.0
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddTread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {

                arr.getAndIncrement(i % arr.length());

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //声明一个20个线程组
        Thread[] t1 = new Thread[20];

        for (int i = 0; i < 20; i++) {
            t1[i] = new Thread(new AddTread());
        }

        for (int i = 0; i < 20; i++) {
            t1[i].start();
        }

        for (int i = 0; i < 20; i++) {
            t1[i].join();
        }
        //输出数组
        System.out.println(arr);
    }

}
