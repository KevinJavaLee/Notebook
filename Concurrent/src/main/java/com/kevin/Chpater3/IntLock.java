package com.kevin.Chpater3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vinlee Xiao
 * @Classname IntLock
 * @Description TODO
 * @Date 2021/9/8 21:09
 * @Version 1.0
 */
public class IntLock implements Runnable{

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public IntLock(int lock){
        this.lock = lock;
    }


    public void run() {

        try {
            if (lock == 1) {
                //中断响应
                try {
                    //锁1
                    System.out.println("--------------------------");
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(600);
                    //锁2
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName());

                    System.out.println("lock1");
                    System.out.println("---------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {

                try {
                    System.out.println("-------------------------");
                    lock2.lockInterruptibly();

                    Thread.sleep(600);

                    lock1.lockInterruptibly();

                    System.out.println("lock2");
                    System.out.println("-------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //如果当前线程持有锁则解锁
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+":线程退出！");
        }


    }

    public static void main(String[] args) throws InterruptedException {
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }
}
