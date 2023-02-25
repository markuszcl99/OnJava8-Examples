package com.markus.onjava.concurrent.task;

import java.util.concurrent.Callable;

/**
 * @author: markus
 * @date: 2023/2/25 9:22 PM
 * @Description: 自私儿童原则：什么都不共享。
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CountingTask implements Callable<Integer> {
    private final int id;

    public CountingTask(int id) {
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
        Integer val = 0;
        for (int i = 0; i < 100; i++) {
            val++;
        }
        System.out.println(id + " " + Thread.currentThread().getName() + " " + val);
        return val;
    }
}
