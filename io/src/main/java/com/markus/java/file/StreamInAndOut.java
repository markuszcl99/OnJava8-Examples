package com.markus.java.file;

import com.markus.java.file.constant.FileConstant;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author: markus
 * @date: 2023/2/16 10:52 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class StreamInAndOut {
    public static void main(String[] args) {
        try (
                Stream<String> input = Files.lines(Paths.get(FileConstant.CURRENT_BASE_PATH, "StreamInAndOut.java"));
                PrintWriter writer = new PrintWriter(FileConstant.CURRENT_BASE_PATH + "StreamInAndOut.txt");
        ) {
            input.map(String::toUpperCase)
                    .forEachOrdered(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}