package com.kevin.Chpater5.FutureMode;

/**
 * @author Vinlee Xiao
 * @Classname Client
 * @Description 获取FutureData ,开启构造RealData线程 ，并在接受请求后，很快返回FutureData
 * @Date 2021/9/17 11:34
 * @Version 1.0
 */
public class Client {

    public Data request(final String queryStr) {

        final FutureData futureData = new FutureData();


        //开启线程构造RealData
        new Thread(){

            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();


        return futureData;

    }

}
