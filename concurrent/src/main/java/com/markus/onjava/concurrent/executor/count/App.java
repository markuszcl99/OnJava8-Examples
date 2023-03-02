package com.markus.onjava.concurrent.executor.count;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author: markus
 * @date: 2023/2/28 10:01 PM
 * @Description: 应用-线程池
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class App {
    private static final int threadSize = 128;
    private static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(
                    threadSize,
                    threadSize,
                    10, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(1000),
                    new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 获取当前机器的CPU核数
        int cores = Runtime.getRuntime().availableProcessors();
        int requestNum = 1000;
        System.out.println("CPU核数 " + cores);
        List<Future<?>> futureList = new ArrayList<>();
        Vector<Long> wholeTimeList = new Vector<>();
        Vector<Long> runTimeList = new Vector<>();

        for (int i = 0; i < requestNum; i++) {
            // IO密集型任务
            //Future<?> future = threadPool.submit(new IOTypeTest(runTimeList, wholeTimeList));
            // CPU密集型任务
            Future<?> future = threadPool.submit(new CPUTypeTest(runTimeList, wholeTimeList));
            futureList.add(future);
        }

        for (Future<?> future : futureList) {
            // 获取线程执行结果
            future.get(requestNum, TimeUnit.SECONDS);
        }

        long wholeTime = 0;
        for (int i = 0; i < wholeTimeList.size(); i++) {
            wholeTime = wholeTimeList.get(i) + wholeTime;
        }

        long runTime = 0;
        for (int i = 0; i < runTimeList.size(); i++) {
            runTime = runTimeList.get(i) + runTime;
        }

        System.out.println("平均每个线程整体花费时间: " + wholeTime / wholeTimeList.size());
        System.out.println("平均每个线程执行花费时间: " + runTime / runTimeList.size());
        // 关闭线程池
        threadPool.shutdown();
    }
}
