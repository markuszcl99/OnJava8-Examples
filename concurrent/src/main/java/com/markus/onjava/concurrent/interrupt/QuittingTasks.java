package com.markus.onjava.concurrent.interrupt;

import com.markus.onjava.Nap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: markus
 * @date: 2023/2/26 8:51 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class QuittingTasks {
    public static final int COUNT = 150;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<QuittableTask> tasks = IntStream.range(1, COUNT)
                .mapToObj(QuittableTask::new)
                .peek(qt -> executor.execute(qt))
                .collect(Collectors.toList());
        new Nap(1);
        tasks.forEach(QuittableTask::quit);
        executor.shutdown();
    }
}
