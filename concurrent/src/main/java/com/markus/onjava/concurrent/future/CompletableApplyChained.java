package com.markus.onjava.concurrent.future;

import com.markus.onjava.Timer;

import java.util.concurrent.CompletableFuture;

/**
 * @author: markus
 * @date: 2023/2/26 9:13 PM
 * @Description: CompletableFuture#thenApply示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CompletableApplyChained {
    public static void main(String[] args) {
        Timer timer = new Timer();
        CompletableFuture<Machine> cf =
                CompletableFuture.completedFuture(new Machine(0))
                        .thenApply(Machine::work)
                        .thenApply(Machine::work)
                        .thenApply(Machine::work)
                        .thenApply(Machine::work);
        System.out.println(timer.duration());
    }
}
