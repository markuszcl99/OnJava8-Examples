package com.markus.onjava.concurrent.task.framework;

/**
 * @author: markus
 * @date: 2023/3/9 10:15 PM
 * @Description: 工作者
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class Worker implements Runnable {


    @Override
    public abstract void run();
}
