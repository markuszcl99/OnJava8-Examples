package com.markus.onjava.concurrent.stream;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: markus
 * @date: 2023/2/25 4:04 PM
 * @Description: Collection和Stream同时出现可能会出现意外
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ParallelStreamPuzzle {
    static class IntGenerator implements Supplier<Integer> {

        private int current = 0;

        @Override
        public Integer get() {
            return current++;
        }
    }

    public static void main(String[] args) {
        List<Integer> x = Stream
                .generate(new IntGenerator()).limit(10)
//                .parallel() // [1] 注释此处后 会稳定输出 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] 但采用并行流后发现每次输出的值都是不稳定的
                .collect(Collectors.toList());;
        System.out.println(x);
    }
}
