package com.markus.onjava.concurrent.future;

import com.markus.onjava.Nap;
import com.markus.onjava.concurrent.interrupt.QuittableTask;
import com.markus.onjava.concurrent.interrupt.QuittingTasks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: markus
 * @date: 2023/2/26 8:57 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class QuittingCompletable {
    public static void main(String[] args) {
        List<QuittableTask> tasks = IntStream.range(1, QuittingTasks.COUNT)
                .mapToObj(QuittableTask::new)
                .collect(Collectors.toList());

        List<CompletableFuture<Void>> cf = tasks.stream()
                .map(CompletableFuture::runAsync)
                .collect(Collectors.toList());
        new Nap(1);
        tasks.forEach(QuittableTask::quit);
        cf.forEach(CompletableFuture::join); // 阻塞 等待所有任务退出后结束
    }
}
