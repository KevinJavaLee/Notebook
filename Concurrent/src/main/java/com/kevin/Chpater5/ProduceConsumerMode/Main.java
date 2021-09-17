package com.kevin.Chpater5.ProduceConsumerMode;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Vinlee Xiao
 * @Classname Main
 * @Description TODO
 * @Date 2021/9/17 10:05
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //定义线程池
        ExecutorService service = Executors.newCachedThreadPool();
        BlockingDeque<PCData> pcData = new LinkedBlockingDeque<>();

        Producer producer1 = new Producer(pcData);
        Producer producer2 = new Producer(pcData);
        Producer producer3 = new Producer(pcData);

        Consumer consumer1 = new Consumer(pcData);
        Consumer consumer2 = new Consumer(pcData);
        Consumer consumer3 = new Consumer(pcData);

        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        Thread.sleep(10 * 1000);
        //停止生产者
        producer1.stop();
        producer2.stop();
        producer3.stop();
        //关闭线程池
        service.shutdown();
    }
}
