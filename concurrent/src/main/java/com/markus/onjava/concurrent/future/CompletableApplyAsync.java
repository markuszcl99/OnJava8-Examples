package com.markus.onjava.concurrent.future;

import com.markus.onjava.Timer;

import java.util.concurrent.CompletableFuture;

/**
 * @author: markus
 * @date: 2023/2/26 9:16 PM
 * @Description: CompletableFuture#thenApplyAsync示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 * thenApplyAsync 与 thenApply的区别就在于 它的调用会立刻返回，不会阻塞等待任务执行完成
 */
public class CompletableApplyAsync {
    public static void main(String[] args) {
        Timer timer = new Timer();
        CompletableFuture<Machine> cf = CompletableFuture.completedFuture(new Machine(0))
                .thenApplyAsync(Machine::work)
                .thenApplyAsync(Machine::work)
                .thenApplyAsync(Machine::work)
                .thenApplyAsync(Machine::work);
        System.out.println(timer.duration());
        System.out.println(cf.join()); //阻塞 等待结果返回
        System.out.println(timer.duration());
    }
}
