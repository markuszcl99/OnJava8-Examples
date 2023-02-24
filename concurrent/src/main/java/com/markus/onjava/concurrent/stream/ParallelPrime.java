package com.markus.onjava.concurrent.stream;


import com.markus.onjava.Timer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/24
 * @Description:
 */
public class ParallelPrime {
    static final int COUNT = 100_000;

    /**
     * 判断 n 是否是素数
     * 素数的概念：指在大于1的整数中只能被1和它本身整除的数
     *
     * @param n
     * @return
     */
    public static boolean isPrime(long n) {
        return LongStream.rangeClosed(2, (long) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    public static void main(String[] args) throws IOException {
        Timer timer = new Timer();
        List<String> primes = LongStream.iterate(2, i -> i + 1)
                .parallel() // [1] 注释前 耗时 574 ms；注释后 耗时1298 ms
                .filter(ParallelPrime::isPrime)
                .limit(COUNT)
                .mapToObj(Long::toString)
                .collect(Collectors.toList());
        System.out.println(timer.duration());
        Files.write(Paths.get("concurrent/src/main/java/com/markus/onjava/concurrent/stream/txt/primes.txt"), primes, StandardOpenOption.CREATE);
    }
}
