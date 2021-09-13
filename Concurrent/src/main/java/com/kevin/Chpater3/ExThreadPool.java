package com.kevin.Chpater3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Vinlee Xiao
 * @Classname ExThreadPool
 * @Description TODO
 * @Date 2021/9/12 16:44
 * @Version 1.0
 */
public class ExThreadPool {

    public static class MyTask implements Runnable{


        private String name;
        public MyTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行！" + ":Tread ID:" + Thread.currentThread().getName() + "-" + name);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {


        ExecutorService es = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("已经执行：" + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出！");

            }
        };

        for (int i = 0; i < 5; i++) {

            MyTask task = new MyTask("Task-GEYM-" + i);

            es.execute(task);
            Thread.sleep(10);
        }

        es.shutdown();
    }
}
