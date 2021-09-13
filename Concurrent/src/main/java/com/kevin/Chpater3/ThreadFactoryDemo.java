package com.kevin.Chpater3;

import java.util.concurrent.*;

/**
 * @author Vinlee Xiao
 * @Classname ThreadFactoryDemo
 * @Description TODO
 * @Date 2021/9/12 16:29
 * @Version 1.0
 */
public class ThreadFactoryDemo {

    public static class MyTask implements Runnable{

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();

        ExecutorService service = new ThreadPoolExecutor(5, 15, 0L,
                TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {

                //创建新的线程
                Thread thread = new Thread(r);
                //设置创建的线程为守护线程
                thread.setDaemon(true);

                //打印线程创建的信息
                System.out.println(System.currentTimeMillis() + ":create it!");

                return thread;



            }
        });

        for (int i = 0; i < 15; i++) {
            service.submit(task);

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
