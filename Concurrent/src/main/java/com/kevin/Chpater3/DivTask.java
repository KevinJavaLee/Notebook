package com.kevin.Chpater3;

import java.util.concurrent.*;

/**
 * @author Vinlee Xiao
 * @Classname DivTask
 * @Description TODO
 * @Date 2021/9/12 16:55
 * @Version 1.0
 */
public class DivTask implements Runnable {

    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;

    }



    @Override
    public void run() {
        double res = a / b;
        System.out.println(res);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());


        for (int i = 0; i < 5; i++) {
            //不会输出堆栈信息
//            pools.submit(new DivTask(100, i));
            //方式一：输出错误的堆栈信息
//            pools.execute(new DivTask(100, i));
            //方式二：输出错误信息的堆栈
            Future<?> future = pools.submit(new DivTask(100, i));
            future.get();
        }
    }
}
