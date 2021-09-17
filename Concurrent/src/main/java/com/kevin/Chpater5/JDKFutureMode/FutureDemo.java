package com.kevin.Chpater5.JDKFutureMode;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author Vinlee Xiao
 * @Classname FutureDemo
 * @Description Guava对FutureTask的拓展
 * @Date 2021/9/17 11:58
 * @Version 1.0
 */
public class FutureDemo {

    public static void main(String[] args) {


        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));


        ListenableFuture<String> task = listeningExecutorService.submit(new RealData("K"));



        task.addListener(()->{

            System.out.println("异步处理完成！");

            System.out.println("输出任务值为：");
            try {
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            System.out.println("main task done!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },MoreExecutors.directExecutor());


    }
}
