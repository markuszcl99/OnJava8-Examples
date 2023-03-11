package com.markus.onjava.concurrent.task.framework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: markus
 * @date: 2023/3/3 11:33 PM
 * @Description: 异步任务
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AsyncTask implements Runnable {


    private volatile Future<AsyncTask> future = null;
    private long startTime;
    private volatile boolean finished = false;
    private volatile boolean succeed = false;

    public AsyncTask() {

    }

    public void start() {
        if (null != future) {
            throw new IllegalStateException(String.format("duplicate async task : %s", this.getClass().getSimpleName()));
        }
        future = AsyncTaskThreadPool.submit(this);
        startTime = System.currentTimeMillis();
    }

    public void waitFromStart() {
        if (!finished) {
            long now = System.currentTimeMillis();
            // 任务执行所耗费的时间
            long elapsed = now - startTime;
            // 任务超时时间
            long timeout = getTimeout();
            // 剩余需要阻塞等待的时间 1
            long remaining = Long.max(1, timeout - elapsed);
            try {
                future.get(remaining, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                succeed = false;
                System.out.println("任务执行失败！");
            } finally {
                finished = true;
            }
        }
    }

    /**
     * 任务的超时时间
     *
     * @return
     */
    public abstract long getTimeout();
}
