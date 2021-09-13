package com.kevin.Chpater3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Vinlee Xiao
 * @Classname ReadWriteLockDemo
 * @Description TODO
 * @Date 2021/9/11 15:34
 * @Version 1.0
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    //读写锁
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();

    private int value;


    public Object handleRead(Lock lock) throws InterruptedException {


        try {

            lock.lock();
            Thread.sleep(1500); //模拟读操作
            return value;

        } finally {
            lock.unlock();
        }


    }


    public void handleWrite(Lock lock,int index) throws InterruptedException {

        try {
            lock.lock(); //模拟写操作
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();


        //匿名内部类
        Runnable readRunnble = new Runnable() {
            public void run() {
                try {
                    readWriteLockDemo.handleRead(lock);
//                    readWriteLockDemo.handleRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                }
            }
        };


        Runnable writeRunnable = new Runnable() {
            public void run() {

                try {
//                    readWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
                    readWriteLockDemo.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        //进行20次读
        for (int i = 0; i < 20; i++) {
            new Thread(readRunnble).start();
        }

        //进行20次写
        for (int i = 0; i < 10; i++) {
            new Thread(writeRunnable).start();

        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
