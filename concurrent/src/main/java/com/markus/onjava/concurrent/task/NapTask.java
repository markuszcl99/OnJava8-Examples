package com.markus.onjava.concurrent.task;

import com.markus.onjava.Nap;

/**
 * @author: markus
 * @date: 2023/2/25 6:03 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class NapTask implements Runnable {
    final int id;

    public NapTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        new Nap(0.1);
        System.out.println(this + " " + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return "NapTask[" + "id=" + id + ']';
    }
}
