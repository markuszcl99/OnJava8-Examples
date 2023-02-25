package com.markus.onjava.concurrent.task;

/**
 * @author: markus
 * @date: 2023/2/25 9:13 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class InterferingTask implements Runnable {

    final int id;

    private static Integer val = 0;

    public InterferingTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            val++;
        }
        System.out.println(id + " " + Thread.currentThread().getName() + " " + val);
    }
}
