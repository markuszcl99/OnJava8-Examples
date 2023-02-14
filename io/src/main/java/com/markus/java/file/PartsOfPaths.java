package com.markus.java.file;

import com.markus.java.file.constant.FileConstant;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/7
 * @Description:
 */
public class PartsOfPaths {
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        Path p = Paths.get(FileConstant.CURRENT_BASE_PATH, "/PartsOfPaths.java");
        for (int i = 0; i < p.getNameCount(); i++) {
            System.out.println(p.getName(i));
        }
        System.out.println("ends with '.java': " + p.endsWith(".java"));
        for (Path path : p) {
            System.out.print(path + ": ");
            System.out.print(p.startsWith(path) + " : ");
            System.out.println(p.endsWith(path));
        }

        System.out.println("Starts with " + p.toAbsolutePath().getRoot() + " " + p.toAbsolutePath().startsWith(p.toAbsolutePath().getRoot()));
    }
}
/** 输出
 * Mac OS X
 * io
 * src
 * main
 * java
 * com
 * markus
 * java
 * file
 * PartsOfPaths.java
 * ends with '.java': false
 * io: true : false
 * src: false : false
 * main: false : false
 * java: false : false
 * com: false : false
 * markus: false : false
 * java: false : false
 * file: false : false
 * PartsOfPaths.java: false : true
 * Starts with / true
 */