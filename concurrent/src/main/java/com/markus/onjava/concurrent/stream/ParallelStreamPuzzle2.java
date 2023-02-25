package com.markus.onjava.concurrent.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: markus
 * @date: 2023/2/25 4:10 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ParallelStreamPuzzle2 {
    /*创建一个双向队列*/
    public static final Deque<String> trace = new ConcurrentLinkedDeque<>();

    static class IntGenerator implements Supplier<Integer> {
        private AtomicInteger current = new AtomicInteger();

        @Override
        public Integer get() {
            trace.add(current.get() + ": " + Thread.currentThread().getName());
            return current.getAndIncrement();
        }
    }

    public static void main(String[] args) throws IOException {
        // 其实流内部的工作流程是：抽象一个可按需生产的无线序列。如果采用并行的方式生成流时，实际上是在让所有的线程都尽可能调用get()方法。
        List<Integer> x = Stream
                .generate(new IntGenerator())
                .limit(10)
                .parallel()
                .collect(Collectors.toList());
        System.out.println(x);
        Files.write(Paths.get("concurrent/src/main/java/com/markus/onjava/concurrent/stream/txt/PSP2.txt"), trace);
    }
}
