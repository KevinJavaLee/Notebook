package com.kevin.Chpater2;

/**
 * @author Vinlee Xiao
 * @Classname AccountingSync2
 * @Description synchronized 作用于静态方法
 * @Date 2021/9/8 19:49
 * @Version 1.0
 */
public class AccountingSync2 implements Runnable {
    static int i = 0;

    public synchronized static void increase() {
        i++;
    }

    public void run() {
        for (int j = 0; j < 100000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingSync2());
        Thread t2 = new Thread(new AccountingSync2());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
