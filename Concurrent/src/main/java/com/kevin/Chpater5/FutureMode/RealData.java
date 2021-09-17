package com.kevin.Chpater5.FutureMode;

/**
 * @author Vinlee Xiao
 * @Classname RealData
 * @Description 负责产生具体的任务
 * @Date 2021/9/17 11:25
 * @Version 1.0
 */
public class RealData implements Data{

    private final String result;

    /**
     * 构造函数对数据进行初始化
     * @param para
     */
    public RealData(String para) {

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < 10; i++) {
            buffer.append(para);

            //模拟任务所花费的时间
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.result = buffer.toString();

    }


    @Override
    public String getResult() {
        return result;
    }

}
