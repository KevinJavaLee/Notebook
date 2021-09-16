package com.kevin.Chpater4;

/**
 * @author Vinlee Xiao
 * @Classname DeadLock
 * @Description TODO
 * @Date 2021/9/16 11:38
 * @Version 1.0
 */
public class DeadLock extends Thread {

    protected Object tool;

    static Object fork1 = new Object();
    static Object fork2 = new Object();

    @Override
    public void run() {

        if (tool == fork1) {
            synchronized (fork1) {

                try {
                    Thread.sleep(1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized(fork2){
                    System.out.println("哲学家A开始吃饭了！");

                }
            }
        }

        if (tool == fork2) {
            synchronized (fork2) {


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork1){
                    System.out.println("哲学家B开始吃饭！");

                }
            }
        }


    }

    public DeadLock(Object object) {

        this.tool = object;

        if (tool == fork1) {
            this.setName("哲学家1");

        }

        if (tool == fork2) {
            this.setName("哲学家2");

        }


    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock lock1 = new DeadLock(fork1);
        DeadLock lock2 = new DeadLock(fork2);

        lock1.start();
        lock2.start();

        Thread.sleep(1000);

    }

}
