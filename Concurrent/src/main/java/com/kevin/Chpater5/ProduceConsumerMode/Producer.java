package com.kevin.Chpater5.ProduceConsumerMode;

import com.kevin.Chpater5.ProduceConsumerMode.PCData;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vinlee Xiao
 * @Classname Producer
 * @Description TODO
 * @Date 2021/9/17 9:41
 * @Version 1.0
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEP_TIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        PCData data = null;
        //声明一个随机数对象
        Random r = new Random();

        System.out.println(Thread.currentThread().getName() + " is going to producing!");
        while (isRunning) {

            //设置休眠的时间
            try {
                Thread.sleep(r.nextInt(SLEEP_TIME));
                data = new PCData(count.getAndIncrement());
                System.out.println(Thread.currentThread().getName()+": "+data + "数据 进入队列中!");
                //判断进入数据缓存区是否是正确的
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + ": " + "Failed to load data!");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public void stop() {
        isRunning = false;
    }
}
