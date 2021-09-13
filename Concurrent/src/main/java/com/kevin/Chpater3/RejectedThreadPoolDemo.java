package com.kevin.Chpater3;

import java.util.concurrent.*;

/**
 * @author Vinlee Xiao
 * @Classname RejectedThreadPoolDemo
 * @Description TODO
 * @Date 2021/9/12 16:19
 * @Version 1.0
 */
public class RejectedThreadPoolDemo {

    public static class MyTask implements Runnable{

        public void run() {

            System.out.println(System.currentTimeMillis() + ":Thread Name!");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 15, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(100), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + " is discarded!");
            }
        });


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.submit(task);
            Thread.sleep(10);

        }
    }
}
