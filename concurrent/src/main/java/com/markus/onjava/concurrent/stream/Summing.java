package com.markus.onjava.concurrent.stream;

import com.markus.onjava.Timer;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/24
 * @Description:
 */
public class Summing {
    static void timeTest(String id, long checkValue, LongSupplier operation) {
        System.out.print(id + ": ");
        Timer timer = new Timer();
        long result = operation.getAsLong();
        if (result == checkValue) {
            System.out.println(timer.duration() + "ms");
        } else {
            System.out.format("result: %d%n checkValue: %d%n", result, checkValue);
        }
    }

//  public static final int SZ = 100_000;

    public static final int SZ = 100_000_000;

//  public static final int SZ = 1_000_000_000;

    /**
     * 高斯公式
     */
    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2;

    public static void main(String[] args) {
        System.out.println(CHECK);
        timeTest("Sum Stream", CHECK,
                () -> LongStream.rangeClosed(0, SZ)
                        .sum());
        timeTest("Sum Stream Parallel", CHECK,
                () -> LongStream.rangeClosed(0, SZ)
                        .parallel()
                        .sum());
        timeTest("Sum Stream Iterated", CHECK,
                () -> LongStream.iterate(0, i -> i + 1)
                        .limit(SZ + 1)
                        .sum());
        timeTest("Sum Stream Iterated Parallel", CHECK,
                () -> LongStream.iterate(0, i -> i + 1)
                        .parallel()
                        .limit(SZ + 1)
                        .sum());

    }
}
