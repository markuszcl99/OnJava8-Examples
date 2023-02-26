package com.markus.onjava.concurrent.future;

import java.util.concurrent.CompletableFuture;

import static com.markus.onjava.concurrent.future.CompletableUtils.showResult;
import static com.markus.onjava.concurrent.future.CompletableUtils.voidResult;

/**
 * @author: markus
 * @date: 2023/2/26 9:25 PM
 * @Description: CompletableFuture API
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CompletableOperations {

    static CompletableFuture<Integer> cfi(int i) {
        return CompletableFuture.completedFuture(i);
    }

    public static void main(String[] args) {
        // 基本的使用
        showResult(cfi(1));
        // 异步执行-CompletableFuture的静态方法
        voidResult(CompletableFuture.runAsync(() -> {
            System.out.println("runAsync");
        }));
        // 空返回的CompletableFuture的空返回情景
        voidResult(cfi(3).thenRunAsync(() -> {
            System.out.println("thenRunAsync");
        }));
        // 形成一个新的CompletableFuture，该方法也是静态方法
        showResult(CompletableFuture.supplyAsync(() -> 99));
        // 依赖Consumer，不会返回结果
        voidResult(cfi(4).thenAcceptAsync(i -> {
            System.out.println("thenAcceptAsync: " + i);
        }));
        // 依赖Function，会返回结果
        showResult(cfi(5).thenApplyAsync(i -> i + 42));
        // 合并两个CompletableFuture
        showResult(cfi(6).thenComposeAsync(i -> cfi(i + 99)));
        CompletableFuture<Integer> cf = cfi(7);
        // 强制设置一个结果
        cf.obtrudeValue(111);
        // 输出被强制设置的结果
        showResult(cf);
        // 返回从当前的CompletionStage生成的CompletableFuture
        showResult(cfi(8).toCompletableFuture());

        cf = new CompletableFuture<>();
        // 传入结果让Future完成执行
        cf.complete(9);
        showResult(cf);
        cf = new CompletableFuture<>();
        cf.cancel(true);
        // 查看当前Future的状态
        System.out.println("cancelled: " + cf.isCancelled());
        System.out.println("completed exceptionally: " + cf.isCompletedExceptionally());
        System.out.println("done: " + cf.isDone());
        System.out.println(cf);
        cf = new CompletableFuture<>();
        // 要么返回CompletableFuture的完整值，要么返回getNow传入的替代参数（如果该Future尚未完成的情况下）
        System.out.println(cf.getNow(777));
        cf = new CompletableFuture<>();
        // dependents 即为依赖项，就是正在等待CompletableFuture完成的CompletableFuture的预估数量
        cf.thenApplyAsync(i -> i + 42).thenApplyAsync(i -> i * 12);
        // 之所以返回1是因为它是两个连续的调用
        System.out.println("dependents: " + cf.getNumberOfDependents());
        cf.thenApplyAsync(i -> i / 2);
        // 之所以返回2是因为它又是一次额外的调用，即在cf完成后，基于此结果fork多个新的任务，一个是两个连续调用，一个是额外调用
        System.out.println("dependents: " + cf.getNumberOfDependents());
        cf.complete(1); // [1]
        showResult(cf); //注释[1]后 会发生阻塞，因为还没传入Future执行完成的结果值
        showResult(cf.thenApplyAsync(i -> i + 42).thenApplyAsync(i -> i * 12));
        showResult(cf.thenApplyAsync(i -> i / 2));
    }
}
