package com.markus.java.io.file;

import com.markus.java.io.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: markus
 * @date: 2023/2/16 10:47 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ReadLineStream {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get(FileConstant.CURRENT_BASE_PATH, "PathInfo.java"))
                .skip(13)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
/** 控制台
 *  * @Author: zhangchenglong06
 */