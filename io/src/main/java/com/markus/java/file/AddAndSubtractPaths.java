package com.markus.java.file;

import com.markus.java.file.constant.FileConstant;

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
