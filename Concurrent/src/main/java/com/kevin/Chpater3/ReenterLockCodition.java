package com.kevin.Chpater3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vinlee Xiao
 * @Classname ReenterLockCodition
 * @Description TODO
 * @Date 2021/9/8 22:32
 * @Version 1.0
 */
public class ReenterLockCodition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();


    public void run() {

        while (true) {

            try {
                lock.lock();
                //让当前锁等待
                condition.await();
                System.out.println(Thread.currentThread().getName() + " is going on!");
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCodition reenterLockCodition = new ReenterLockCodition();
        Thread t1 = new Thread(reenterLockCodition);
        t1.start();
        Thread.sleep(2000);

        //Condition.await()方式时，要求线程持有相关的重入锁
        lock.lock();
        //唤醒线程 signal()方法之后要求释放相关的锁，给唤醒的线程
        condition.signal();
        lock.unlock();

        //释放
    }
}
