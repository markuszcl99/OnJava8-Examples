package com.markus.onjava.concurrent.stream;

import java.util.Arrays;

/**
 * @author: markus
 * @date: 2023/2/24 11:04 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Summing4 {
    public static void main(String[] args) {
        System.out.println(Summing3.CHECK);
        Long[] aL = new Long[Summing3.SZ + 1];
        Arrays.parallelSetAll(aL, i -> (long) i);
        Summing.timeTest("Long Parallel",
                Summing3.CHECK,
                () -> Arrays.stream(aL)
                        .parallel()
                        .reduce(0L, Long::sum));
    }
}
