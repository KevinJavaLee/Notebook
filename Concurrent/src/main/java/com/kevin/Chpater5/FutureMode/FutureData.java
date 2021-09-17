package com.kevin.Chpater5.FutureMode;

/**
 * @author Vinlee Xiao
 * @Classname FutureData
 * @Description TODO
 * @Date 2021/9/17 11:25
 * @Version 1.0
 */
public class FutureData implements Data {
    protected RealData realData;
    //装载数据是否完成
    protected boolean isReady = false;


    public synchronized void  setRealData(RealData realData) {

        //准备好了直接返回
        if (isReady) {
            return;
        }

        this.realData = realData;
        //标记已经装载完成
        isReady = true;

        //唤醒等待的程序
        notifyAll();
    }

    @Override
    public synchronized String getResult() {

        while (!isReady) {

            try {
                //等待唤醒
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据装载完毕！");
        return realData.getResult();
    }
}
