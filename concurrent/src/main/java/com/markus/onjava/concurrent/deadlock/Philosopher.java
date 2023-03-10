package com.markus.onjava.concurrent.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author: markus
 * @date: 2023/2/25 10:19 PM
 * @Description: 哲学家
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Philosopher implements Runnable {
    private final int seat;
    private final StickHolder left, right;

    public Philosopher(int seat, StickHolder left, StickHolder right) {
        this.seat = seat;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "P" + seat;
    }

    @Override
    public void run() {
        while (true) {
            right.pickUp();
            left.pickUp();
            System.out.println(this + " eating");
            right.putDown();
            left.putDown();
        }
    }
}
