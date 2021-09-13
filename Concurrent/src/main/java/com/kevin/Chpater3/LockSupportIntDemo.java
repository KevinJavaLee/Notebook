package com.kevin.Chpater3;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Vinlee Xiao
 * @Classname LockSupportIntDemo
 * @Description LockSupport的使用
 * @Date 2021/9/12 10:06
 * @Version 1.0
 */
public class LockSupportIntDemo {

    public static Object u = new Object();

    public static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    public static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name) {
            super.setName(name);

        }

        @Override
        public void run() {
            synchronized(u){
                System.out.println("in:" + getName());
                //挂起
                LockSupport.park();
                if (Thread.interrupted()) {
                    System.out.println(getName() + "：当前线程被中断！");
                }

                System.out.println(getName() + "执行结束！");

            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
//        t1.interrupt();
        //对线程
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

    }
}
