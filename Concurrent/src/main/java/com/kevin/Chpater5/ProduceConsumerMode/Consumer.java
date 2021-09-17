package com.kevin.Chpater5.ProduceConsumerMode;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author Vinlee Xiao
 * @Classname Consumer
 * @Description TODO
 * @Date 2021/9/17 9:53
 * @Version 1.0
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;
    private final static int SLEEP_TMIE = 1000;


    public Consumer(BlockingQueue<PCData> queue) {

        this.queue = queue;
    }

    @Override
    public void run() {


        Random r = new Random();
        System.out.println(Thread.currentThread().getName() + " Consumer is consuming!");

        while (true) {

            try {
                PCData data = queue.take();

                //计算平方
                if (data != null) {

                    int result = data.getIntData() * data.getIntData();
                    int mul = data.getIntData();
                    System.out.println(Thread.currentThread().getName() + ":  " + mul + "*" + mul + "=" + result);

                    Thread.sleep(SLEEP_TMIE);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.interrupted();
            }
        }
    }
}
