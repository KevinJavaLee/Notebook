package com.kevin.Chpater4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vinlee Xiao
 * @Classname ThreadLocalDateDemo
 * @Description ThreadLocal的使用
 * @Date 2021/9/15 20:10
 * @Version 1.0
 */
public class ThreadLocalDateDemo {


    static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable{
        int i = 0;
        public ParseDate(int i){
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //如果当前线程不持有SimpleDateFormat对象实例，就新建一个并设置到当前线程
                if (t1.get()==null){
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

                }
                Date t = t1.get().parse("2020-09-15 19:29:" + i % 60);
                System.out.println(i + ":" + t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //线程池的容量
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            service.execute(new ParseDate(i));

        }
    }
}
