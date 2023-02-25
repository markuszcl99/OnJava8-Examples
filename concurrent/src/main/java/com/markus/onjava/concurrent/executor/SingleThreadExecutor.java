package com.markus.onjava.concurrent.executor;

import com.markus.onjava.Nap;
import com.markus.onjava.concurrent.task.NapTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author: markus
 * @date: 2023/2/25 6:08 PM
 * @Description: 单线程 线程池
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(0, 10)
                .mapToObj(NapTask::new)
                .forEach(executorService::execute);
        System.out.println("All tasks submitted");
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            System.out.println(Thread.currentThread().getName() + " awaiting termination");
            new Nap(0.1);
        }
    }
}
