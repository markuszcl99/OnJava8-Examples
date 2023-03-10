package com.markus.onjava.concurrent.task.framework;

import java.util.concurrent.*;

/**
 * @author: markus
 * @date: 2023/3/8 8:40 AM
 * @Description: 异步任务线程池
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class AsyncTaskThreadPool {

    private static ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(500);
    private final static ThreadPoolExecutor pool =
            new ThreadPoolExecutor(
                    16,
                    16,
                    60,
                    TimeUnit.SECONDS, queue,
                    new ThreadPoolExecutor.DiscardOldestPolicy());

    @SuppressWarnings("unchecked")
    public static Future<AsyncTask> submit(AsyncTask task) {
        return (Future<AsyncTask>) pool.submit(task);
    }
}
