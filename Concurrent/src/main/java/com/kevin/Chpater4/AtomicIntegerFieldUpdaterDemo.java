package com.kevin.Chpater4;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Vinlee Xiao
 * @Classname AtomicIntegerFieldUpdaterDemo
 * @Description AtomicIntegerFieldUpdate让普通变量享受原子操作
 * @Date 2021/9/16 16:36
 * @Version 1.0
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static class Candidate {
        int id;
        volatile int score;

        public Candidate(){};
    }


    final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {


        //声明对象
        final Candidate candidate = new Candidate();

        Thread[] threads = new Thread[10000];

        for (int i = 0; i < 10000; i++) {

            threads[i] = new Thread(){
                @Override
                public void run() {

                    //30%得票的概率
                    if (Math.random() > 0.5) {
                        //增加1
                        allScore.incrementAndGet();
                        scoreUpdater.incrementAndGet(candidate);
                    }
                }

            };

            threads[i].start();

        }

        for (int i = 0; i < 10000; i++) {

            threads[i].join();

        }

        System.out.println(allScore);
        System.out.println(candidate.score);
    }
}
