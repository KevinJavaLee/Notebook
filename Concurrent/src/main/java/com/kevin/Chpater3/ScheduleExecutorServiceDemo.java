package com.kevin.Chpater3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Vinlee Xiao
 * @Classname ScheduleExecutorServiceDemo
 * @Description TODO
 * @Date 2021/9/12 16:02
 * @Version 1.0
 */
public class ScheduleExecutorServiceDemo {




    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);


        //如果前面的任务没有完成，则调度不会完成
        //从开始 2 秒后执行下一次任务
        //scheduledExecutorService的使用
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    System.out.println(System.currentTimeMillis());
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, 0, 2, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    System.out.println(System.currentTimeMillis());

                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }, 0, 2,TimeUnit.SECONDS);
    }
}
