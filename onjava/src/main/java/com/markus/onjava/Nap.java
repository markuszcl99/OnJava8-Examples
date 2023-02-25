package com.markus.onjava;

import java.util.concurrent.TimeUnit;

/**
 * @author: markus
 * @date: 2023/2/25 5:58 PM
 * @Description: 睡眠
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Nap {
    public Nap(double t) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) (1000 * t));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Nap(double t, String msg) {
        this(t);
        System.out.println(msg);
    }
}
