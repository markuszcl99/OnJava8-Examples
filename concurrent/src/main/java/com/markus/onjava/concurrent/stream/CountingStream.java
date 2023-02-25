package com.markus.onjava.concurrent.stream;

import com.markus.onjava.concurrent.task.CountingTask;

import java.util.stream.IntStream;

/**
 * @author: markus
 * @date: 2023/2/25 9:37 PM
 * @Description: 采用并行流的方式完成计算
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CountingStream {
    public static void main(String[] args) {
        Integer result = IntStream.range(0, 10)
                .parallel()
                .mapToObj(CountingTask::new)
                .map(ct -> {
                    try {
                        return ct.call();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .reduce(0, Integer::sum);
        System.out.println(result);
    }
}
