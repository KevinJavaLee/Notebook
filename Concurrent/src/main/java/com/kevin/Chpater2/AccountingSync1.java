package com.kevin.Chpater2;

/**
 * @author Vinlee Xiao
 * @Classname AccountingSync1
 * @Description TODO
 * @Date 2021/9/8 19:45
 * @Version 1.0
 */
public class AccountingSync1 implements Runnable {

    static AccountingSync1 accountingSync1 = new AccountingSync1();
    static  int i =0;

    public synchronized void increase(){
        i++;
    }

    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(accountingSync1);
        Thread t2 = new Thread(accountingSync1);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
