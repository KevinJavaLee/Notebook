package com.kevin.Chpater3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vinlee Xiao
 * @Classname TimeLock
 * @Description tryLock的使用
 * @Date 2021/9/8 21:32
 * @Version 1.0
 */
public class TimeLock implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();


    public void run() {

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
                System.out.println("get lock success" + Thread.currentThread().getName());
            }else {
                System.out.println("get lock failed---"+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();

        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();


    }
}
