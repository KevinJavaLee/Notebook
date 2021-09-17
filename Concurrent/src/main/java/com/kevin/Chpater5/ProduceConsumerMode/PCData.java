package com.kevin.Chpater5.ProduceConsumerMode;

/**
 * @author Vinlee Xiao
 * @Classname PCData
 * @Description TODO
 * @Date 2021/9/17 9:40
 * @Version 1.0
 */
public final class PCData {

    private final int intData;


    public PCData(int intData) {
        this.intData = intData;
    }


    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data: " + intData;
    }
}
