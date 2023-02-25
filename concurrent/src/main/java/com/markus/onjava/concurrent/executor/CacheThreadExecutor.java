package com.markus.onjava.concurrent.executor;

import com.markus.onjava.concurrent.task.CountingTask;
import com.markus.onjava.concurrent.task.InterferingTask;
import com.markus.onjava.concurrent.task.NapTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: markus
 * @date: 2023/2/25 9:03 PM
 * @Description: 缓存线程池 可复用当前可用的线程
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CacheThreadExecutor {
    public static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
//        executeInterferingTask();
        executeCountingTask();
    }

    private static void demo1() {
        IntStream.range(0, 10)
                .mapToObj(NapTask::new)
                .forEach(executor::execute);
        executor.shutdown();
    }

    /**
     * 多线程情况对单例对象的写操作是非线程安全的
     */
    private static void executeInterferingTask() {
        IntStream.range(0, 10)
                .mapToObj(InterferingTask::new)
                .forEach(executor::execute);
        executor.shutdown();
    }

    private static Integer get(Future<Integer> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 自私儿童原则，利用Callable使得所有数据都不共享，每个线程使用的数据都是线程私有的，因此不会造成线程不安全的问题
     * @throws InterruptedException
     */
    private static void executeCountingTask() throws InterruptedException {
        List<CountingTask> countingTaskList = IntStream.range(0, 10)
                .mapToObj(CountingTask::new)
                .collect(Collectors.toList());
        // Future 是Java 5引入的机制，它可以允许我们提交一个Callable任务，并且不会阻塞主流程，直到我们显示的调用Future#get()方法才会阻塞
        List<Future<Integer>> futures = executor.invokeAll(countingTaskList);
        Integer sum = futures.stream()
                .map(CacheThreadExecutor::get)
                .reduce(0, Integer::sum);
        System.out.println("sum = " + sum);
        executor.shutdown();
    }
}
