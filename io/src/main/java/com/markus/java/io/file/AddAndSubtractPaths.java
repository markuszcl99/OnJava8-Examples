package com.markus.java.io.file;

import com.markus.java.io.file.constant.FileConstant;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/2/8
 * @Description: 添加或删除路径片段
 * relativize 删除基准路径
 * resolve 增加路径
 */
public class AddAndSubtractPaths {
    static Path base = Paths.get("..", "..", "..").toAbsolutePath().normalize();

    static void show(int id, Path result) {
        if (result.isAbsolute()) {
            // result 在 路径首部删除base基准路径形成新的路径
            System.out.println("(" + id + ")r" + base.relativize(result));
        } else {
            System.out.println("(" + id + ")" + result);
        }
        try {
            System.out.println("RealPath: " + result.toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(base);
        Path p = Paths.get(FileConstant.CURRENT_BASE_PATH, "/AddAndSubtractPaths.java").toAbsolutePath();
        show(1, p);
        Path convoluted = p.getParent().getParent().resolve("strings").resolve("..").resolve(p.getParent().getFileName());
        show(2, convoluted);
        show(3, convoluted.normalize());

        Path p2 = Paths.get("..", "..");
        show(4, p2);
        // normalize 意为删除了冗余名称元素的路径
        // 例如 ./ 直接删除即可
        // 例如 strings/../ 直接删除两个元素即可
        show(5, p2.normalize());
        show(6, p2.toAbsolutePath());

        Path p3 = Paths.get(".").toAbsolutePath();
        // p3在自身路径后面追加p2组成新的路径
        Path p4 = p3.resolve(p2);
        show(7, p4);
        show(8, p4.normalize());

        Path p5 = Paths.get("").toAbsolutePath();
        show(9, p5);
        show(10, p5.resolveSibling("strings"));
        show(11, Paths.get("nonexistent"));
    }
}
/** 输出
 *  Mac OS X
 * /Users
 * (1)rzhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/AddAndSubtractPaths.java
 * RealPath: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file/AddAndSubtractPaths.java
 * (2)rzhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/strings/../file
 * java.nio.file.NoSuchFileException: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/strings/../file
 * (3)rzhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file
 * RealPath: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples/io/src/main/java/com/markus/java/file
 * (4)../..
 * RealPath: /Users/zhangchenglong
 * (5)../..
 * RealPath: /Users/zhangchenglong
 * (6)rzhangchenglong/IdeaProjects/OnJava8-Examples/../..
 * RealPath: /Users/zhangchenglong
 * (7)rzhangchenglong/IdeaProjects/OnJava8-Examples/./../..
 * RealPath: /Users/zhangchenglong
 * (8)rzhangchenglong
 * RealPath: /Users/zhangchenglong
 * (9)rzhangchenglong/IdeaProjects/OnJava8-Examples
 * RealPath: /Users/zhangchenglong/IdeaProjects/OnJava8-Examples
 * (10)rzhangchenglong/IdeaProjects/strings
 * java.nio.file.NoSuchFileException: /Users/zhangchenglong/IdeaProjects/strings
 * (11)nonexistent
 * java.nio.file.NoSuchFileException: nonexistent
 */
