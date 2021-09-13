package com.kevin.Chpater3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Vinlee Xiao
 * @Classname CountTask
 * @Description TODO
 * @Date 2021/9/12 17:14
 * @Version 1.0
 */
public class CountTask extends RecursiveTask<Long> {

    public static final long THRESHOLD = 10000L;
    private long start;
    private long end;


    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        long sum = 0;
        boolean isCompute = (end - start) < THRESHOLD;

        if (isCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
//                System.out.println(sum);

            }

        }else {

            //分成100个小的任务
            long step = (end - start) / 100;

            ArrayList<CountTask> countTasks = new ArrayList<>();
            long position = start;

            for (int i = 0; i < 100; i++) {

                //计算的最后一个下标
                long lastIndex = position + step;
                if (lastIndex > end) {
                    lastIndex = end;
                }

                CountTask task = new CountTask(position, lastIndex);

                //坐标后移

                position = position + step + 1;

                //在列表中添加子任务
                countTasks.add(task);
                //开启子线程
                task.fork();

            }

            for (CountTask task : countTasks) {
                sum += task.join();
            }

        }


        return sum;
    }

    public static void main(String[] args) {

        //线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CountTask countTask = new CountTask(0, 10000001L);

        ForkJoinTask<Long> result = forkJoinPool.submit(countTask);

        try {
            Long sum = result.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
