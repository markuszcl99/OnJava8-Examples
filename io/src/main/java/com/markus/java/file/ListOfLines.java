package com.markus.java.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: markus
 * @date: 2023/2/15 10:39 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ListOfLines {
    public static void main(String[] args) throws IOException {
        Files.readAllLines(Paths.get("io/src/main/resources/file_writer.txt"))
                .forEach(System.out::println);
    }
}
/** 控制台
 * Hello,IO
 */