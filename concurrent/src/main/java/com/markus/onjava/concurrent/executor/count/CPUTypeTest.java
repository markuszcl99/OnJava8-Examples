package com.markus.onjava.concurrent.executor.count;

import com.markus.onjava.Nap;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author: markus
 * @date: 2023/2/28 10:02 PM
 * @Description: CPU密集型-线程池大小设置测试
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CPUTypeTest implements Runnable {

    // 整体执行时间，包括在队列中等待的时间
    List<Long> wholeTimeList;

    // 真正执行时间
    List<Long> runTimeList;

    // 任务初始化时间
    private long initStartTime = 0;

    public CPUTypeTest(List<Long> runTimeList, List<Long> wholeTimeList) {
        this.initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    /**
     * 判断是否素数
     *
     * @param number
     * @return
     */
    public boolean isPrime(final int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(i -> i % number == 0);
    }

    public boolean isPrime2(final int number) {
        if (number <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public long countPrimes(final int lower, final int upper) {
        int total = 0;
        for (int i = lower; i <= upper; i++) {
            if (isPrime2(i)) {
                total++;
            }
        }
        return total;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        new Nap(1);
        countPrimes(1, 100_0000);
        long end = System.currentTimeMillis();

        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        System.out.println("单个线程花费时间：" + (end - start));
    }
}
