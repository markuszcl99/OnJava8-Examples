package com.markus.onjava.concurrent.interrupt;

import com.markus.onjava.Nap;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: markus
 * @date: 2023/2/26 8:47 PM
 * @Description: 退出任务示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class QuittableTask implements Runnable {
    final int id;

    public QuittableTask(int id) {
        this.id = id;
    }

    private AtomicBoolean running = new AtomicBoolean(true);

    public void quit() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get())
            new Nap(0.1);
        System.out.print(id + " ");
    }
}
