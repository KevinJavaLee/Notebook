package com.kevin.Chpater4;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Vinlee Xiao
 * @Classname AtomicStampedReferenceDemo
 * @Description TODO
 * @Date 2021/9/16 16:03
 * @Version 1.0
 */
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {

        //模拟多个线程同时对数据进行更新
        for (int i = 0; i < 3; i++) {
            final int stamp = money.getStamp();
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        while (true) {

                            Integer m = money.getReference();

                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, stamp, stamp + 1)) {

                                    System.out.println("余额小于20，充值成功！ " + money.getReference());
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    break;
                                }else {
                                    System.out.println("余额大于20！");
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    break;
                                }
                            }

                        }
                    }
                }
            }.start();


            new Thread(){
                @Override
                public void run() {

                    for (int j = 0; j < 100; j++) {
                        while (true) {

                            final int stamp = money.getStamp();
                            Integer m = money.getReference();

                            if (m > 10) {

                                if (money.compareAndSet(m, m - 10, stamp, stamp + 1)) {
                                    System.out.println("余额大于10元！ 消费10元  " + money.getReference());
                                    break;

                                }else {
                                    System.out.println("余额小于10元，不够消费金额！");
                                    break;
                                }

                            }

                        }

                    }
                }
            }.start();
            //模拟多个线程对数据进行消费

        }
    }
}
