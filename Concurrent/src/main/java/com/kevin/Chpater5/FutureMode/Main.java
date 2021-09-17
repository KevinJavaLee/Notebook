package com.kevin.Chpater5.FutureMode;

/**
 * @author Vinlee Xiao
 * @Classname Main
 * @Description TODO
 * @Date 2021/9/17 11:39
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();

        Data data = client.request("kevin");
        System.out.println("请求完毕!");


        //用Sleep表示其他也出处理的逻辑

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //输出真实返回的数据
        System.out.println("真实的数据为：" + data.getResult());

    }
}
