package com.markus.onjava.concurrent.deadlock;

import com.markus.onjava.Nap;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @author: markus
 * @date: 2023/2/25 10:23 PM
 * @Description: 哲学家的晚餐演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DiningPhilosophers {
    /*筷子持有者*/
    private StickHolder[] sticks;
    /*哲学家*/
    private Philosopher[] philosophers;

    public DiningPhilosophers(int n) {
        sticks = new StickHolder[n];
        Arrays.setAll(sticks, i -> new StickHolder());
        philosophers = new Philosopher[n];
        Arrays.setAll(philosophers, i -> new Philosopher(i, sticks[i], sticks[(i + 1) % n]));
//        philosophers[1] = new Philosopher(1, sticks[0], sticks[1]); // 通过将第2位哲学家颠倒拿放筷子的顺序来修正死锁
        Arrays.stream(philosophers)
                .forEach(CompletableFuture::runAsync);

    }

    public static void main(String[] args) {
        new DiningPhilosophers(5);
        new Nap(100, "Shutdown");
    }
}
