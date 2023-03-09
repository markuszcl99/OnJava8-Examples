package com.markus.onjava.concurrent.task.framework;

import java.util.concurrent.Future;

/**
 * @author: markus
 * @date: 2023/3/3 11:33 PM
 * @Description: 异步任务
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class AsyncTask implements Runnable{


    private volatile Future<AsyncTask> future = null;
    private long startTime;
    private volatile boolean finished = false;
    private volatile boolean succeed = false;

    public AsyncTask(){

    }
}
