package com.kevin.Chpater2;

/**
 * @author Vinlee Xiao
 * @Classname NoVisibility
 * @Description TODO
 * @Date 2021/9/8 16:29
 * @Version 1.0
 */
public class NoVisibility {
    private static volatile boolean ready;
    private static volatile int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready) {
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(2000);

    }
}
