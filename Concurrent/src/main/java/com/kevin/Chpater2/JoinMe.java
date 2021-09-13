package com.kevin.Chpater2;

/**
 * @author Vinlee Xiao
 * @Classname JoinMe
 * @Description TODO
 * @Date 2021/9/8 16:09
 * @Version 1.0
 */


//join() 和yield()的作用
//yiedld()让出cpu资源
public class JoinMe {

    public volatile static int i = 0;

    //静态内部类
    public static class AddTread extends Thread{

        @Override
        public void run() {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AddTread addTread = new AddTread();
        addTread.start();
        //谦让
        addTread.join();
        System.out.println(i);

    }
}
