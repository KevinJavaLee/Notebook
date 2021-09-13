package com.kevin.Chpater3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Vinlee Xiao
 * @Classname SemaDemo
 * @Description 信号量的使用：信号量（Semaphore）
 * @Date 2021/9/11 15:22
 * @Version 1.0
 */
public class SemaDemo implements Runnable {

    //信号量 同时允许5个线程访问
    final Semaphore semp = new Semaphore(5);



    public void run() {


        //获得信号量的一个准入许可
        try {
            semp.acquire();

            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + ":Done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //线程访问资源结束后，以使其他等待许可可以对资源进行访问
            semp.release();
        }
    }


    public static void main(String[] args) {
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemaDemo demo = new SemaDemo();

        for (int i = 0; i < 20; i++) {
            executorService.submit(demo);

        }
    }



}
