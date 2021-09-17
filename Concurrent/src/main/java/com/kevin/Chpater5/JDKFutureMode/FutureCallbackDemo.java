package com.kevin.Chpater5.JDKFutureMode;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Vinlee Xiao
 * @Classname FutureCallbackDemo
 * @Description TODO
 * @Date 2021/9/17 14:45
 * @Version 1.0
 */
public class FutureCallbackDemo {
    public static void main(String[] args) {

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));


        ListenableFuture<String> task = listeningExecutorService.submit(new RealData("V"));


        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                System.out.println("异步处理成功,v=" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {

                System.out.println("异步处理失败，e" + throwable);

            }
        }, MoreExecutors.directExecutor());



    }
}
