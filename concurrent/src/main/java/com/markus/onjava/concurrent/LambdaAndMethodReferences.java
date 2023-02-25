package com.markus.onjava.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: markus
 * @date: 2023/2/25 9:51 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class LambdaAndMethodReferences {
    static class NotRunnable {
        public void go() {
            System.out.println("Not Runnable");
        }
    }

    static class NotCallable {
        public Integer get() {
            System.out.println("Not Callable");
            return 1;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> System.out.println("Lambda1"));
        executor.submit(new NotRunnable()::go);
        Future<Integer> future1 = executor.submit(() -> {
            System.out.println("Lambda2");
            return 2;
        });
        Future<Integer> future2 = executor.submit(new NotCallable()::get);
        System.out.println(future1.get());
        System.out.println(future2.get());
        executor.shutdown();
    }
}
