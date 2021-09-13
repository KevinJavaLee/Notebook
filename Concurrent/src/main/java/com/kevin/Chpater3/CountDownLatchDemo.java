package com.kevin.Chpater3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vinlee Xiao
 * @Classname CountDownLatchDemo
 * @Description TODO
 * @Date 2021/9/11 15:58
 * @Version 1.0
 */
public class CountDownLatchDemo implements Runnable{

    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();



    public void run() {

        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getName() + "Check complete!");
            //计数
            end.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(demo);
        }

        //等待检查
        try {
            end.await();
            System.out.println("Fire");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //关闭线程池
        service.shutdown();
    }
}
