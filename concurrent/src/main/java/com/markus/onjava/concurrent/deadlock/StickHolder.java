package com.markus.onjava.concurrent.deadlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: markus
 * @date: 2023/2/25 10:12 PM
 * @Description: 筷子持有者
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class StickHolder {
    private static class Chopstick {
    }

    private Chopstick stick = new Chopstick();

    private BlockingQueue<Chopstick> holder = new ArrayBlockingQueue<>(1);

    public StickHolder() {
        putDown();
    }

    /**
     * 拿起筷子
     */
    public void pickUp() {
        try {
//            holder.take(); // 不可用时会阻塞
            holder.poll(100, TimeUnit.MILLISECONDS);// 超时自己中断，不再尝试去获取锁
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 放下筷子
     */
    public void putDown() {
        try {
            holder.put(stick);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
