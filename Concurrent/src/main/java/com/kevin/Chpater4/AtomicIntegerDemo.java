package com.kevin.Chpater4;

import org.apache.commons.math3.analysis.function.Add;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vinlee Xiao
 * @Classname AtomicIntegerDemo
 * @Description TODO
 * @Date 2021/9/16 10:14
 * @Version 1.0
 */
public class AtomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();

    public static class AddTread implements Runnable{


        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet();

            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new AddTread());

        }

        for (int j = 0; j < 10; j++) {
            threads[j].start();

        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();

        }
        System.out.println(i);
    }
}
