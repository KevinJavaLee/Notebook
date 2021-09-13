package com.kevin.Chpater3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vinlee Xiao
 * @Classname tryLock
 * @Description TODO
 * @Date 2021/9/8 21:41
 * @Version 1.0
 */
public class tryLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public tryLock(int lock) {
        this.lock = lock;
    }

    public void run() {

        //如果为线程1
        if (lock == 1) {

            while (true) {
                //线程1先申请锁1
                try {
                    if (lock1.tryLock()) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //再申请锁2
                        if (lock2.tryLock()) {

                            try {
                                System.out.println(Thread.currentThread().getName()+":My Jod done!");
                                return;
                            } finally {
                                //释放锁
                                lock2.unlock();
                            }


                        }

                    }
                } finally {
                    lock1.unlock();
                }
            }
        }else {

            while (true) {
                //首先申请锁2

                try {
                    if (lock2.tryLock()) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                            if (lock1.tryLock()) {

                                try {
                                    System.out.println(Thread.currentThread().getName() + ":My Job done!");
                                    return;
                                } finally {
                                    lock1.unlock();
                                }
                            }


                    }
                } finally {
                    lock2.unlock();
                }

            }

        }

    }

    public static void main(String[] args) {
        tryLock lock1 = new tryLock(1);
        tryLock lock2 = new tryLock(2);

        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);

        t1.start();
        t2.start();

    }
}
