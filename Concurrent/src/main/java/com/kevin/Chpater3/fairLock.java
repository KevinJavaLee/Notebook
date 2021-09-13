package com.kevin.Chpater3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vinlee Xiao
 * @Classname fairLock
 * @Description TODO
 * @Date 2021/9/8 22:08
 * @Version 1.0
 */
public class fairLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock(true);





    public void run() {
        while (true)

            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁！");

            } finally {
                lock.unlock();
            }
    }

    public static void main(String[] args) {
        fairLock fairLock = new fairLock();
        Thread t1 = new Thread(fairLock);
        Thread t2 = new Thread(fairLock);

        t1.start();
        t2.start();
    }
}
