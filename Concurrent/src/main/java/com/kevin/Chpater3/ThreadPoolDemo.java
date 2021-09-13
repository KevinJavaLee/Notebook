package com.kevin.Chpater3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vinlee Xiao
 * @Classname ThreadPoolDemo
 * @Description TODO
 * @Date 2021/9/12 15:54
 * @Version 1.0
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable{

        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread Name:" + Thread.currentThread().getName());


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //1.创建固定的线程池

        MyTask task = new MyTask();
        //固定的线程池
//        ExecutorService service = Executors.newFixedThreadPool(5);
        //线程数量不固定 可根据实际情况来调整线程的数量的线程池
        ExecutorService service = Executors.newCachedThreadPool();


        for (int i = 0; i < 10; i++) {
            service.submit(task);

        }
    }
}
