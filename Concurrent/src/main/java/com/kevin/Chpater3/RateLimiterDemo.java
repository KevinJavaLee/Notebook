package com.kevin.Chpater3;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author Vinlee Xiao
 * @Classname RateLimiterDemo
 * @Description 限流
 * @Date 2021/9/12 10:37
 * @Version 1.0
 */
public class RateLimiterDemo {

    static RateLimiter limiter = RateLimiter.create(2000);


    public static class Task implements Runnable {
        public void run() {
            System.out.println(System.currentTimeMillis());

        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
              //控制流量
//            limiter.acquire();

            //过载了就抛弃
            if (!limiter.tryAcquire()) {
                continue;
            }
            new Thread(new Task()).start();

        }
    }
}
