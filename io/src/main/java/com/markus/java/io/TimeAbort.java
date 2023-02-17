package com.markus.java.io;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/13
 * @Description:
 */
public class TimeAbort {
    private volatile boolean restart = true;

    public TimeAbort(double t, String msg) {
        CompletableFuture.runAsync(() -> {
            try {
                while (restart) {
                    restart = false;
                    TimeUnit.MILLISECONDS.sleep((int) (1000 * t));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(msg);
            System.exit(0);
        });
    }

    public TimeAbort(double t) {
        this(t, "TimeAbort " + t);
    }

    public void restart() {
        restart = true;
    }
}
