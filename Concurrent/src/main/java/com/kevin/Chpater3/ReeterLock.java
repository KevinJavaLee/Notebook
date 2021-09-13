package com.kevin.Chpater3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vinlee Xiao
 * @Classname ReeterLock
 * @Description synchronized 可重入锁
 * @Date 2021/9/8 20:56
 * @Version 1.0
 */
public class ReeterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i =0;




    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();

            try {
                i++;
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReeterLock reeterLock = new ReeterLock();
        Thread t1 = new Thread(reeterLock);
        Thread t2 = new Thread(reeterLock);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
