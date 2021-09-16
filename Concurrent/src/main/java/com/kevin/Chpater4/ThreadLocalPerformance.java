package com.kevin.Chpater4;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Vinlee Xiao
 * @Classname ThreadLocalPerformance
 * @Description TODO
 * @Date 2021/9/16 9:26
 * @Version 1.0
 */
public class ThreadLocalPerformance {

    public static final int GEN_COUNT = 100000;
    public static final int THREAD_COUNT = 40;
    //定义线程池
    static ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
    //被多个线程共享的实例对象
    public static Random rnd = new Random(123);

    public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){

        @Override
        protected Random initialValue() {
            //设立随机数种子
            return new Random(123);
        }
    };


    public static class RndTask implements Callable<Long>{

        int mode;
        public RndTask(int mode) {
            this.mode = mode;

        }


        public Random getRandom() {
            if (mode == 0) {
                return rnd;
            } else if (mode == 1) {
                return tRnd.get();
            }else {
                return null;
            }

        }



        @Override
        public Long call() throws Exception {

            long b = System.currentTimeMillis();

            for (long i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }

            long e = System.currentTimeMillis();

            System.out.println(Thread.currentThread().getName() + "spend" + (e - b) + "ms");

            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<Long>[] futures = new Future[THREAD_COUNT];

        //向线程池中提交线程任务
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = service.submit(new RndTask(0));

        }

        long totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += futures[i].get();

        }

        System.out.println("多个线程共享一个对象：" + totalTime+" ms");


        totalTime = 0;

        //ThreadLocal的情况
        for (int i = 0; i < THREAD_COUNT; i++) {

            futures[i] = service.submit(new RndTask(1));

        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += futures[i].get();

        }

        System.out.println("ThreadLocal:" + totalTime + "ms");

        //关闭线程池
        service.shutdown();
    }
}
