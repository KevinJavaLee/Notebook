package com.kevin.Chpater2;

/**
 * @author Vinlee Xiao
 * @Classname ThreadGroupName
 * @Description TODO
 * @Date 2021/9/8 16:39
 * @Version 1.0
 */
public class ThreadGroupName implements Runnable {
    public static void main(String[] args) {
        ThreadGroup printGroup = new ThreadGroup("PrintGroup");
        Thread thread = new Thread(printGroup,new ThreadGroupName());
        Thread thread1 = new Thread(printGroup,new ThreadGroupName());
        thread.start();
        thread1.start();
        //输出活跃的线程数
        System.out.println(printGroup.activeCount());
        //打印线程组的所有线程信息
        printGroup.list();
    }


    public void run() {
       String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-"+Thread.currentThread().getName();
       while(true){
           System.out.println("I am " + groupAndName);


           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
