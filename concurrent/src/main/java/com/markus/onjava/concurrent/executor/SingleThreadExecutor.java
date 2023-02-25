package com.markus.onjava.concurrent.executor;

import com.markus.onjava.Nap;
import com.markus.onjava.concurrent.task.InterferingTask;
import com.markus.onjava.concurrent.task.NapTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author: markus
 * @date: 2023/2/25 6:08 PM
 * @Description: 单线程 线程池，它最大的好处就是同一时刻只能执行一项任务，这些任务永远也不会相互影响，保证了线程安全，这种现象我们成为线程封闭。
 * 因为将多个任务放在一个线程上去执行，这样影响了程序的提速
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SingleThreadExecutor {
    public static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executeInterferingTask();
    }

    private static void demo() {
        IntStream.range(0, 10)
                .mapToObj(NapTask::new)
                .forEach(executor::execute);
        System.out.println("All tasks submitted");
        executor.shutdown(); // 完成线程池中的任务后结束，不再接受新的任务
//        executorService.shutdownNow(); // 停止接受新的任务，并且通过中断的方式将当前正在运行的任务停止，慎用此方法
        while (!executor.isTerminated()) {
            System.out.println(Thread.currentThread().getName() + " awaiting termination");
            new Nap(0.1);
        }
    }

    /**
     * 单线程 线程池操作单例数据是正常的 因为本身就只有一个线程
     */
    private static void executeInterferingTask() {
        IntStream.range(0, 10)
                .mapToObj(InterferingTask::new)
                .forEach(executor::execute);
        executor.shutdown();
    }
}
