package com.markus.onjava.concurrent.stream;

import java.util.Arrays;

/**
 * @author: markus
 * @date: 2023/2/24 10:44 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Summing3 {
    static long basicSum(Long[] la) {
        long sum = 0;
        int size = la.length;
        for (int i = 0; i < size; i++) {
            sum += la[i];
        }
        return sum;
    }

    // 接近内存溢出的最大值
    public static final int SZ = 10_000_000;

    /**
     * 高斯公式
     */
    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2;

    public static void main(String[] args) {
        System.out.println(CHECK);
        Long[] la = new Long[SZ + 1];
        Arrays.parallelSetAll(la, i -> (long) i);
        Summing.timeTest("Long Array Stream Reduce", CHECK, () -> Arrays.stream(la).reduce(0L, Long::sum));
        Summing.timeTest("Long BasicSum", CHECK, () -> basicSum(la));
        Summing.timeTest("Long parallelPrefix", CHECK, () -> {
            Arrays.parallelPrefix(la, Long::sum);
            return la[la.length - 1];
        });
    }
}
