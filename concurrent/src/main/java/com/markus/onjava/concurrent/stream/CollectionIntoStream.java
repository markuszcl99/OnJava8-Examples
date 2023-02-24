package com.markus.onjava.concurrent.stream;

import com.markus.onjava.Rand;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: markus
 * @date: 2023/2/24 11:12 PM
 * @Description: 集合到Stream的转换
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CollectionIntoStream {
    public static void main(String[] args) {
        List<String> strings = Stream
                .generate(new Rand.String(5))
                .limit(10)
                .collect(Collectors.toList());
        strings.forEach(System.out::println);
        // 转换为Stream，以执行更多操作
        String result = strings
                .stream()
                .map(String::toUpperCase)
                .map(s -> s.substring(2))
                .reduce(":", (s1, s2) -> s1 + s2);
        System.out.println(result);
    }
}
