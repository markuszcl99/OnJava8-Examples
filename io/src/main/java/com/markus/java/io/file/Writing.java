package com.markus.java.io.file;

import com.markus.java.io.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * @author: markus
 * @date: 2023/2/16 10:33 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Writing {
    static Random rand = new Random(47);

    static final int SIZE = 1000;

    public static void main(String[] args) throws IOException {
        // 将字节写入一个文件
        byte[] bytes = new byte[SIZE];
        rand.nextBytes(bytes);
//        Files.write(Paths.get(FileConstant.CURRENT_BASE_PATH, "bytes.txt"), bytes, Charset.forName("charset"));
        Files.write(Paths.get(FileConstant.CURRENT_BASE_PATH, "bytes.txt"), bytes);
        System.out.println("bytes.txt: " + Files.size(Paths.get(FileConstant.CURRENT_BASE_PATH, "bytes.txt")));

        // 将实现了Iterable接口的类的对象写入一个文件
        List<String> lines = Files.readAllLines(Paths.get("io/src/main/resources/file_writer.txt"));
        Files.write(Paths.get("io/src/main/resources/file_writer.txt"), lines);
        System.out.println("file_writer.txt: " + Files.size(Paths.get("io/src/main/resources/file_writer.txt")));
    }
}
/**
 * 控制台
 * bytes.txt: 1000
 * file_writer.txt: 9
 */