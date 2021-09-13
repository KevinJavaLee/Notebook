package com.kevin.Chpater2;

/**
 * @author Vinlee Xiao
 * @Classname AccountingSync
 * @Description TODO
 * @Date 2021/9/8 19:40
 * @Version 1.0
 */


//synchronized 的作用 作用于：对象
public class AccountingSync implements Runnable {

    static AccountingSync accountingSync = new AccountingSync();
    static  int i =0;


    public void run() {
        for (int j = 0; j < 10000; j++) {
            synchronized (accountingSync) {
                i++;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(accountingSync);
        Thread thread1 = new Thread(accountingSync);

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(i);
    }
}
