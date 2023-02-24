package com.markus.onjava;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author: markus
 * @date: 2023/2/24 10:37 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Timer {
    private long start = System.nanoTime();

    public long duration() {
        return NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    public static long duration(Runnable test) {
        Timer timer = new Timer();
        test.run();
        return timer.duration();
    }
}
