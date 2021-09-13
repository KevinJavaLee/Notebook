package com.kevin.Chpater3;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Vinlee Xiao
 * @Classname CyclicBarrier
 * @Description TODO
 * @Date 2021/9/11 16:11
 * @Version 1.0
 */
public class CyclicBarrierDemo {



    public static class Soldiedr implements Runnable{

        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldiedr(CyclicBarrier cyclicBarrier, String soldierName) {
            this.cyclicBarrier = cyclicBarrier;
            this.soldier = soldierName;
        }


        public void run() {

            try {
                //士兵到齐后执行的操作
                cyclicBarrier.await();
                doWork();
                //等到士兵完成工作后
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void doWork() {

            try {

                Thread.sleep(new Random().nextInt(10) * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(soldier + ":任务完成！");
        }
    }


    public static class BarrierRun implements Runnable{

        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }


        public void run() {

            if (flag) {
                System.out.println("司令：[士兵" + N + "个，任务完成！]");
            }else {
                System.out.println("司令：[士兵" + N + "个，集合完毕！]");
                flag = true;
            }

        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("集合完毕！");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Soldiedr(cyclicBarrier, "士兵" + (i+1)));
            allSoldier[i].start();

        }
    }

}
