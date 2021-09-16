package com.kevin.Chpater4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Vinlee Xiao
 * @Classname AtomicReferenceDemo
 * @Description TODO
 * @Date 2021/9/16 15:39
 * @Version 1.0
 */
public class AtomicReferenceDemo {
    static AtomicReference<Integer> money = new AtomicReference<>();

    public static void main(String[] args) {

        //设置初始值为19 小于20
        money.set(19);

        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {

                    while (true) {
                        while (true) {
                            Integer m = money.get();

                            //如果小于20元 ，就充值20
                            if (m < 20) {
                                money.compareAndSet(m, m + 20);
                                System.out.println("卡内余额小于20，充值成功！ " + money.get());
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                break;
                            }else {
                                System.out.println("卡内余额大于20！");
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }



                        }
                    }
                }
            }.start();
        }



        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    while (true) {
                        Integer m = money.get();

                        if (m > 10) {
                            System.out.println("大于10元");

                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("成功消费10元！余额：" + money.get());
                                break;
                            } else {
                                System.out.println("余额不足10元！");
                                break;
                            }
                        }

                    }

                }
            }
        }.start();
    }



}
