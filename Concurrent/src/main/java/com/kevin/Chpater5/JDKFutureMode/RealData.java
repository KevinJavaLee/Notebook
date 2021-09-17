package com.kevin.Chpater5.JDKFutureMode;

import java.util.concurrent.*;

/**
 * @author Vinlee Xiao
 * @Classname RealData
 * @Description TODO
 * @Date 2021/9/17 11:49
 * @Version 1.0
 */
public class RealData implements Callable<String> {

    private String data;


    public RealData(String para) {
        this.data = para;

    }



    @Override
    public String call() throws Exception {

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < 10; i++) {

            try {
                //模拟产生数据耗尽的时间
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            buffer.append(data);

        }
        return buffer.toString();
    }

    public static void main(String[] args) {


        FutureTask<String> futureTask = new FutureTask<>(new RealData("a"));

        //建立线程池
        ExecutorService service = Executors.newFixedThreadPool(1);

        service.submit(futureTask);

        System.out.println("请求完毕！");

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //出现问题 futureTask可能产生延时的情况

        try {
            System.out.println("任务产生的数据：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
